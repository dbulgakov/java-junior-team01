package server;

import server.history.History;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ChatUserManager {
    private List<ChatUser> clients;
    private ReadWriteLock clientsLock;
    private History history;

    public ChatUserManager() {
        this.clients = new ArrayList<>();
        this.clientsLock = new ReentrantReadWriteLock();
    }

    public void send(String messageToSend, String roomName) throws IOException {
        clientsLock.readLock().lock();
        for (ChatUser client : clients) {
            synchronized (client) {
                if(client.getRoomName().equals(roomName)) {
                    client.send(messageToSend);
                }
            }
        }
        clientsLock.readLock().unlock();
    }

    public void addUser(ChatUser chatUser) {
        clientsLock.writeLock().lock();
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
}
