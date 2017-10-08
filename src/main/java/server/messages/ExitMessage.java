package server.messages;

import server.ChatUser;

import java.time.LocalDateTime;

public class ExitMessage extends Message {
	
	public ExitMessage(LocalDateTime datetime, String message, ChatUser chatUser) {
		super(LocalDateTime.from(datetime), chatUser);
	}
	
	@Override
	public String getText() {
		return "";
	}
	
	@Override
	public void process() {
		chatUser.exit();
	}
}
