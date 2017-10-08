package server.messages;

import server.ChatUser;
import server.ChatUserManager;
import server.history.History;

import java.time.LocalDateTime;

public class MessageFabric {

    private static History history;
    private static ChatUserManager chatUserManager;

    public static void setHistory(History history) {
        MessageFabric.history = history;
    }

    public static void setChatUserManager(ChatUserManager chatUserManager) {
        MessageFabric.chatUserManager = chatUserManager;
    }

    public static Message getMessage(LocalDateTime dateTime, ChatUser chatUser, String text) {
        String command = text.split(" ")[0];
        Message message = null;
        switch (command) {
            case "/snd":
                message = new SenderMessage(dateTime, chatUser, text.substring(5), chatUserManager);
                break;
            case "/hist":
                message = new HistoryMessage(dateTime, chatUser, history, chatUserManager);
                break;
            case "/chid":
                message = new RenameMessage(dateTime, chatUser, text.substring(6), chatUserManager);
                break;
            case "/exit":
                break;
            default:
                message = null;
                break;
        }
        return message;
    }
}
