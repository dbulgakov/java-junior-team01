package server;

import server.history.History;
import server.messages.Message;
import server.messages.MessageFabric;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

/**
 * Класс обрабатывает серверную часть
 *
 * @version 1.1
 * @autor Team-01
 */
public class Server {
    private static ChatUserManager chatUserManager;
    private static History history;
    private static ReadWriteLock clientsLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        chatUserManager = new ChatUserManager();
        history = new History();
        MessageFabric.setHistory(history);
        MessageFabric.setChatUserManager(chatUserManager);

        serverLoop(Server::clientLoop);
    }

    private static void serverLoop(Consumer<Socket> toDo) {
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
            chatUserManager.addUser(chatUser);
            while (true) {
                Message message = chatUser.getMessage(LocalDateTime.now());
                message.process();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
