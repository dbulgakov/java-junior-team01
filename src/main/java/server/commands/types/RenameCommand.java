package server.commands.types;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;

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
        if(!chatUserManager.rename(name, chatUser))chatUser.send("Name is already exist.");
    }
}
