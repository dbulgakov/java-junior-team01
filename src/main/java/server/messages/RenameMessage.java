package server.messages;

import server.ChatUser;

import java.io.IOException;
import java.time.LocalDateTime;

public class RenameMessage extends Message {

    public RenameMessage(LocalDateTime dateTime, ChatUser chatUser) {
        super(dateTime, chatUser);
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    protected void process() throws IOException {

    }
}
