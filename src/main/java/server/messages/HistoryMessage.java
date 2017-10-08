package server.messages;

import server.ChatUser;
import server.history.History;

import java.io.IOException;
import java.time.LocalDateTime;

public class HistoryMessage extends Message {
	private History history;
	
	public HistoryMessage(LocalDateTime dateTime, ChatUser chatUser, History history) {
		super(dateTime, chatUser);
		this.history = history;
	}
	
	@Override
	public String getText() {
		return history.getHistory();
	}
	
	@Override
	protected void process() throws IOException {
		getChatUser().send(history.getHistory());
	}
}
