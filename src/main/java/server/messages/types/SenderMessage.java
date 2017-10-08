package server.messages.types;

import server.ChatUser;
import server.ChatUserManager;
import server.messages.Message;
import server.messages.formatter.MessageFormatter;

import java.io.IOException;
import java.time.LocalDateTime;

public class SenderMessage extends Message {
    private String text;

    public SenderMessage(LocalDateTime dateTime, ChatUser chatUser, String text, ChatUserManager chatUserManager) {
        super(dateTime, chatUser, chatUserManager);
        this.text = text;
        this.chatUser = chatUser;
    }

    @Override
    public String getText() {
        return MessageFormatter.format(chatUser.getName(), dateTime, text);
    }

    @Override
    public void process() throws IOException {
        chatUserManager.getHistory().addMessage(this);
        chatUserManager.send(getText());
    }
}
