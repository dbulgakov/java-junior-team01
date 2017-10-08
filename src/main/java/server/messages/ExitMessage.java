package server.messages;

import server.ChatUser;

import java.time.LocalDateTime;

public class ExitMessage extends Message {
	
	public ExitMessage(LocalDateTime datetime, String message, ChatUser chatUser) {
		super(datetime, message, chatUser);
	}
	
	@Override
	public void process() {
		chatUser.exit();
	}
	
	
}
