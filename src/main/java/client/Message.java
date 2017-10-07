package client;


public abstract class Message {
	
	private String message;
	
	Message(String message) {
		this.message = message;
	}
	
	protected abstract void processNewMessageInternal();
	
	
}
