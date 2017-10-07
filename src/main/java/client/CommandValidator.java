package client;

public class CommandValidator {
	
	private static final String COMMAND_SEND = "/snd";
	
	public static boolean validateMessage(String message) {
		return message.startsWith(COMMAND_SEND) & message.length() <= 150;
	}
}
