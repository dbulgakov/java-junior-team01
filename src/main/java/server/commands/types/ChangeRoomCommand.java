package server.commands.types;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;

public class ChangeRoomCommand extends Command {
    private String text;
    public ChangeRoomCommand(LocalDateTime dateTime, ChatUser chatUser, ChatUserManager chatUserManager, String text) {
        super(dateTime, chatUser, chatUserManager);
        this.text = text;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void process() throws IOException {
        chatUser.setRoomName(text);
        chatUser.send("you are in the room '" + text.substring(5) + "'");
    }
}
