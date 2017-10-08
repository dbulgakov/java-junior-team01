package server.messages;

import server.ChatUser;

import java.time.LocalDateTime;

public class MessageFabric {
	
	
	public static Message getMessage(LocalDateTime dateTime, ChatUser chatUser, String text){
		String command = text.split("/",1)[0];
		Message message;
		switch (command) {
			case "snd":
				message = new SenderMessage(dateTime, chatUser, text.substring(4));
				break;
			case "hist":
				message = new HistoryMessage(dateTime, chatUser);
				break;
			case "chid":
				message = new RenameMessage(dateTime, chatUser, text.substring(5));
				break;
			case "exit":
				break;
			default:
				
				break;
		}
		return message;
	}
}
