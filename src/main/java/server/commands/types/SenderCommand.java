package server.commands.types;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;
import server.commands.formatter.CommandFormatter;

import java.io.IOException;
import java.time.LocalDateTime;

public class SenderCommand extends Command {
    private String text;

    public SenderCommand(LocalDateTime dateTime, ChatUser chatUser, String text, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
        this.text = text;
        this.chatUser = chatUser;
    }

    @Override
    public String getText() {
        return CommandFormatter.format(chatUser.getName(), dateTime, text);
    }

    @Override
    public void process() throws IOException {
        chatUserManager.getHistory().addMessage(this);
        chatUserManager.send(getText(),chatUser.getRoomName());
    }
}
