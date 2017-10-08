package server.messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageFormatter {
	public static String  format(String name, LocalDateTime dateTime, String text){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formattedDateTime = formatter.format(dateTime);
		return name + " " + formattedDateTime + " " + text;
	}
}
