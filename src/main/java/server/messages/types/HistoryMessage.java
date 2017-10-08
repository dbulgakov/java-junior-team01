package server.messages.types;

import server.ChatUser;
import server.ChatUserManager;
import server.messages.Message;

import java.io.IOException;
import java.time.LocalDateTime;

public class HistoryMessage extends Message {
    public HistoryMessage(LocalDateTime dateTime, ChatUser chatUser, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
    }

    @Override
    public String getText() {
        return chatUserManager.getHistory().getHistory();
    }

    @Override
    public void process() throws IOException {
        chatUser.send(chatUserManager.getHistory().getHistory());
    }
}
