package server.messages.types;

import server.ChatUser;
import server.ChatUserManager;
import server.messages.Message;

import java.time.LocalDateTime;

public class ExitMessage extends Message {

    public ExitMessage(LocalDateTime datetime, ChatUser chatUser, ChatUserManager chatUserManager) {
        super(LocalDateTime.from(datetime), chatUser, chatUserManager);
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void process() {
        chatUserManager.removeUser(chatUser);
    }
}
