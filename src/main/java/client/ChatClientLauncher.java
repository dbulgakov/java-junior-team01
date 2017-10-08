package client;

/**
 * Класс служит для запуска клиентской части
 * @version 1.0
 * @autor Team-01
 */
public final class ChatClientLauncher {
    private ChatClientLauncher() {
    }


    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("localhost", 9999);
        chatClient.start();
    }
}
