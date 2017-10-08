package server.messages;


import server.ChatUser;
import server.ChatUserManager;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class Message {
    protected LocalDateTime dateTime;
    protected ChatUser chatUser;
    protected ChatUserManager chatUserManager;

    public Message(LocalDateTime dateTime, ChatUser chatUser, ChatUserManager chatUserManager) {
        this.dateTime = dateTime;
        this.chatUser = chatUser;
        this.chatUserManager = chatUserManager;
    }

    public abstract String getText();

    public LocalDateTime getDatetime() {
        return dateTime;
    }


    public ChatUser getChatUser() {
        return chatUser;
    }

    public abstract void process() throws IOException;

}
