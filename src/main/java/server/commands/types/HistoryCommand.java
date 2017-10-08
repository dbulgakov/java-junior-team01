package server.commands.types;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;

public class HistoryCommand extends Command {
    public HistoryCommand(LocalDateTime dateTime, ChatUser chatUser, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
    }

    @Override
    public String getText() {
        return chatUserManager.getHistory().getHistory(chatUser.getRoomName());
    }

    @Override
    public void process() throws IOException {
        chatUser.send(chatUserManager.getHistory().getHistory(chatUser.getRoomName()));
    }
}
