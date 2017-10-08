package server.commands.types;

import server.chat.manager.ChatUserManager;
import server.chat.user.ChatUser;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Класс определяет команду изменения комнаты
 * @autor Team-01
 */
public class ChangeRoomCommand extends Command {
    private String roomName;
    public ChangeRoomCommand(LocalDateTime dateTime, ChatUser chatUser, ChatUserManager chatUserManager, String text) {
        super(dateTime, chatUser, chatUserManager);
        this.roomName = text;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void process() throws IOException {
        chatUserManager.setRoomName(roomName,chatUser);
        chatUserManager.send("you are in the room '" + roomName.substring(5) + "'",chatUser);
        chatUserManager.send("Info: " + chatUser.getName() + " entered room", roomName);
    }
}
