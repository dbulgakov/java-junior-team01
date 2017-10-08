package server.commands.types;

import server.chat.ChatUser;
import server.chat.ChatUserManager;
import server.commands.Command;

import java.time.LocalDateTime;
/**
 * Класс определяет команду выхода из чятика.
 * @autor Team-01
 */
public class ExitCommand extends Command {

    public ExitCommand(LocalDateTime datetime, ChatUser chatUser, ChatUserManager chatUserManager) {
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
