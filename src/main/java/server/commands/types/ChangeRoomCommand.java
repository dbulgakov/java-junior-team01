package server.commands.types;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;

public class ChangeRoomCommand extends Command {
    public ChangeRoomCommand(LocalDateTime dateTime, ChatUser chatUser, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void process() throws IOException {

    }
}
