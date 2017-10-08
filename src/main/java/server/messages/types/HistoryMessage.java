package server.messages.types;

import server.ChatUser;
import server.ChatUserManager;
import server.history.History;
import server.messages.Message;

import java.io.IOException;
import java.time.LocalDateTime;

public class HistoryMessage extends Message {
    private History history;

    public HistoryMessage(LocalDateTime dateTime, ChatUser chatUser, History history, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
        this.history = history;
    }

    @Override
    public String getText() {
        return history.getHistory();
    }

    @Override
    public void process() throws IOException {
        chatUser.send(history.getHistory());
    }
}
