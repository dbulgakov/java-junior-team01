package server.messages;

import server.ChatUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SenderMessage extends Message {
	private String text;
	private LocalDateTime dateTime;
	
	public SenderMessage(LocalDateTime dateTime, ChatUser chatUser, String text) {
		this.text = text;
		this.chatUser =chatUser;
	}
	
	@Override
	public String getText() {
		return MessageFormatter.format(chatUser.getName(),dateTime,text);
	}
	
	@Override
	protected void process() throws IOException {
		chatUser.send(getText());
	}
	
	
}
