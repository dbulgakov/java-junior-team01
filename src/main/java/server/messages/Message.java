package server.messages;


import server.ChatUser;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class Message {
    protected LocalDateTime dateTime;
    protected ChatUser chatUser;

    public Message(LocalDateTime dateTime, ChatUser chatUser) {
        this.dateTime = dateTime;
        this.chatUser = chatUser;
    }

    public abstract String getText();

    public LocalDateTime getDatetime() {
        return dateTime;
    }


    public ChatUser getChatUser() {
        return chatUser;
    }

    protected abstract void process() throws IOException;

}
