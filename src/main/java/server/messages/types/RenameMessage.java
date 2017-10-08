package server.messages.types;

import server.ChatUser;
import server.ChatUserManager;

import java.io.IOException;
import java.time.LocalDateTime;

public class RenameMessage extends Message {
    private String name;

    public RenameMessage(LocalDateTime dateTime, ChatUser chatUser, String name, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
        this.name = name;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void process() throws IOException {
        chatUser.rename(name);
    }
}
