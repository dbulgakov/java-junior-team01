package server.commands.types;

import server.chat.manager.ChatUserManager;
import server.chat.user.ChatUser;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Класс определяет команду переименования пользователя.
 * @autor Team-01
 */
public class RenameCommand extends Command {
    private String name;

    public RenameCommand(LocalDateTime dateTime, ChatUser chatUser, String name, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
        this.name = name;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void process() throws IOException {
        if(!chatUserManager.rename(name, chatUser)){
            chatUserManager.send("Name is already exist.",chatUser);
        }
    }
}
