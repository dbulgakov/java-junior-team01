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
    private int userCounter = 0;


    public ChatUserManager() {
        this.clients = new ArrayList<>();
        this.clientsLock = new ReentrantReadWriteLock();
    }

    public void send(String messageToSend, String roomName) throws IOException {
        clientsLock.readLock().lock();
        for (ChatUser client : clients) {
            synchronized (client) {
                if (client.getRoomName().equals(roomName)) {
                    client.send(messageToSend);
                }
            }
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
}
