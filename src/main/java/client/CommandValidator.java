package client;

public class CommandValidator {
	
	private static final String COMMAND_SEND = "/snd";
	
	public static boolean validateMessage(String message) {
		
		if ( message.startsWith(COMMAND_SEND) ) {
			return true;
		} else {
			return false;
		}
	}
}
