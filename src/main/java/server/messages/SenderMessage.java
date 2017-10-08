package server.messages;

import server.ChatUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SenderMessage extends Message {
	private String text;
	private LocalDateTime dateTime;
	
	public SenderMessage(LocalDateTime dateTime, ChatUser chatUser, String text) {
		this.text = text;
		this.chatUser =
	}
	
	@Override
	protected void process() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDateTime = getDatetime().format(formatter);
		ChatUser chatUser = getChatUser();
		String formattedMessage = chatUser.getName() + " " + dateTime + ": " + text;
		setDataMessage(getFormatter().formatString(chatUser.getName(),formattedDateTime,getMessage().replaceFirst("/snd","")));
		chatUser.sendMessage(formattedMessage);
	}
	
	
}
