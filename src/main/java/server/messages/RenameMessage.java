package server.messages;

import server.ChatUser;

import java.io.IOException;
import java.time.LocalDateTime;

public class RenameMessage extends Message {
	private String name;
	
	public RenameMessage(LocalDateTime dateTime, ChatUser chatUser, String name) {
		super(dateTime, chatUser);
		this.name = name;
	}
	
	@Override
	public String getText() {
		return "";
	}
	
	@Override
	protected void process() throws IOException {
		chatUser.rename(name);
	}
}
