package server;

import server.messages.Message;
import server.messages.MessageFabric;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ChatUser {
	private String name = "";
	private BufferedWriter out;
	private BufferedReader in;
	
	public ChatUser(BufferedWriter out, BufferedReader in) {
		this.out = out;
		this.in = in;
	}
	
	
	public void send(String message) throws IOException {
		synchronized (out) {
			out.write(message);
			out.newLine();
			out.flush();
		}
	}
	
	public Message getMessage(LocalDateTime dateTime) throws IOException {
		return (MessageFabric.getMessage(dateTime, this, in.readLine()));
	}
	
	public void exit() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void rename(String name) {
		this.name = name;
	}
}
