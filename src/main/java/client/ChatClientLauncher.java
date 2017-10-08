package client;

/** Класс служит для запуска клиентской части
 * @autor  Team-01
 * @version 1.1
 */
public class ChatClientLauncher {
    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("localhost", 9999);
        chatClient.start();
    }
}
