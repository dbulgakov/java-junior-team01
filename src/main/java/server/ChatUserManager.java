package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ChatUserManager {
    private List<ChatUser> clients;
    private ReadWriteLock clientsLock;

    public ChatUserManager() {
        this.clients = new ArrayList<>();
        this.clientsLock = new ReentrantReadWriteLock();
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
        clients.add(chatUser);
        clientsLock.writeLock().unlock();
    }

    public void removeUser(ChatUser chatUser) {
        clientsLock.writeLock().lock();
        clients.remove(chatUser);
        clientsLock.writeLock().unlock();
    }
}
