package server;

import server.chat.manager.ChatUserManager;
import server.chat.user.ChatUser;
import server.commands.Command;
import server.commands.creator.CommandFabric;
import server.history.History;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * Класс обрабатывает серверную часть
 *
 * @author Team-01
 * @version 1.1
 */
public final class Server {

    private Server() {

    }

    private static ChatUserManager chatUserManager;

    public static void main(String[] args) {
        chatUserManager = new ChatUserManager();
        chatUserManager.setHistory(new History());
        CommandFabric.setChatUserManager(chatUserManager);
        serverLoop(Server::clientLoop);
        chatUserManager.getHistory().flushAllBuffer();
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
        try (
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream())));
                BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(clientSocket.getInputStream())))) {
            ChatUser chatUser = new ChatUser(out, in);
            chatUserManager.addUser(chatUser);
            while (chatUser.isActive()) {
                Command command = chatUserManager.getCommand(LocalDateTime.now(), chatUser);
                command.process();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
