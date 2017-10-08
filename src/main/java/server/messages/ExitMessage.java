package server.messages;

import server.ChatUser;
import server.ChatUserManager;

import java.time.LocalDateTime;

public class ExitMessage extends Message {
	
	public ExitMessage(LocalDateTime datetime, String message, ChatUser chatUser, ChatUserManager chatUserManager) {
		super(LocalDateTime.from(datetime), chatUser, chatUserManager);
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
