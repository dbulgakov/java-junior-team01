package server;

import server.messages.Message;
import server.messages.MessageFabric;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
/** Класс обрабатывает серверную часть
 * @autor  Team-01
 * @version 1.1
 */
public class Server {
	private static List <ChatUser> clients;
	private static ReadWriteLock clientsLock = new ReentrantReadWriteLock();
	private static MessageFabric messageFabric = new MessageFabric();
	
	public static void main(String[] args) {
		clients = new ArrayList <>();
		serverLoop(Server::clientLoop);
	}
	
	private static void serverLoop(Consumer <Socket> toDo) {
		try (ServerSocket listener = new ServerSocket(9999)) {
			
			while (true) {
				Socket clientSocket = listener.accept();
				new Thread(() -> toDo.accept(clientSocket)).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void clientLoop(Socket clientSocket) {
		try (BufferedWriter out = (new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream()))));
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())));) {
			ChatUser chatUser = new ChatUser(out, in);
			clientsLock.writeLock().lock();
			clients.add(chatUser);
			clientsLock.writeLock().unlock();
			while (true) {
				Message message = chatUser.getMessage(LocalDateTime.now());
				message.process();
			}
//			clients.remove(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
