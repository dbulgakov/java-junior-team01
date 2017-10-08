package server;

import server.commands.Command;
import server.history.History;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * Класс управляет списком пользователей чятика в общем и каждым пользователем в частности.
 * @autor Team-01
 */
public class ChatUserManager {
    private List<ChatUser> clients;

    private ReadWriteLock clientsLock;
    private History history;
    private int userCounter = 0;


    public ChatUserManager() {
        this.clients = new ArrayList<>();
        this.clientsLock = new ReentrantReadWriteLock();
    }

    public void send(String messageToSend, String roomName) throws IOException {
        clientsLock.readLock().lock();
        for (ChatUser client : clients) {
            if (client.getRoomName().equals(roomName)) {
                client.send(messageToSend);
            }
        }
        clientsLock.readLock().unlock();
    }

    public void send(String messageToSend, ChatUser chatUser) throws IOException {
        chatUser.send(messageToSend);
    }

    public void send(String messageToSend) throws IOException {
        clientsLock.readLock().lock();
        for (ChatUser client : clients) {
            client.send(messageToSend);
        }
        clientsLock.readLock().unlock();
    }

    public void addUser(ChatUser chatUser) {
        clientsLock.writeLock().lock();
        try {
            chatUser.rename("Anonymous" + userCounter);
            userCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients.add(chatUser);
        clientsLock.writeLock().unlock();
    }

    public void removeUser(ChatUser chatUser) {
        clientsLock.writeLock().lock();
        clients.remove(chatUser);
        clientsLock.writeLock().unlock();
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public boolean rename(String name, ChatUser chatUser) {
        for (ChatUser client : clients) {
            if (client.getName().equals(name)) {
                return false;
            }
        }
        try {
            chatUser.rename(name);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setRoomName(String roomName, ChatUser chatUser){
        chatUser.setRoomName(roomName);
    }
    public String getRoomName(ChatUser chatUser){
        return chatUser.getRoomName();
    }

    public Command getCommand(LocalDateTime localDateTime,ChatUser chatUser) throws IOException {
        return chatUser.getCommand(localDateTime);
    }
}
