package client;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChatSender implements Runnable {
	PrintWriter out;
	BufferedReader in;
	String message;
	
	ChatSender(PrintWriter out, BufferedReader in, String message) {
		this.out = out;
		this.in = in;
	}
	
	@Override
	public void run() {
		out.println(message);
		out.flush();
	}
}
