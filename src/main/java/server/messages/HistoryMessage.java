package server.messages;

import server.ChatUser;

import java.io.IOException;
import java.time.LocalDateTime;

public class HistoryMessage extends Message{
	private String history;
	public HistoryMessage(LocalDateTime dateTime, ChatUser chatUser,String text) {
	
	}
	
	@Override
	public String getText() {
		return history;
	}
	
	@Override
	protected void process() throws IOException{
		getChatUser().send(history);
	}
}
