package server.commands.types;

import server.chat.manager.ChatUserManager;
import server.chat.user.ChatUser;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Класс определяет команду отображения истории.
 * @autor Team-01
 */
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
