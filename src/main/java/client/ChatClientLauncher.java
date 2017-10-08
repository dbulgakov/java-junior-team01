package client;


public class ChatClientLauncher {
    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("localhost", 9999);
        chatClient.start();
    }
}
