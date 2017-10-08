package server.messages;

import server.ChatUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SenderMessage extends Message {
	private String text;
	
	public SenderMessage(LocalDateTime dateTime, ChatUser chatUser, String text) {
		super(dateTime, chatUser);
		this.text = text;
		this.chatUser = chatUser;
	}
	
	@Override
	public String getText() {
		return MessageFormatter.format(chatUser.getName(), dateTime, text);
	}
	
	@Override
	public void process() throws IOException {
		chatUser.send(getText());
	}
}
