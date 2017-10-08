package server.commands.creator;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;
import server.commands.types.ExitCommand;
import server.commands.types.HistoryCommand;
import server.commands.types.RenameCommand;
import server.commands.types.SenderCommand;

import java.time.LocalDateTime;

public class CommandFabric {
    private static ChatUserManager chatUserManager;

    public static void setChatUserManager(ChatUserManager chatUserManager) {
        CommandFabric.chatUserManager = chatUserManager;
    }

    public static Command getMessage(LocalDateTime dateTime, ChatUser chatUser, String text) {
        if (text == null) {
            return new ExitCommand(dateTime, chatUser, chatUserManager);
        }
        String command = text.split(" ")[0];

        Command message;

        switch (command) {
            case "/snd":
                message = new SenderCommand(dateTime, chatUser, text.substring(5), chatUserManager);
                break;
            case "/hist":
                message = new HistoryCommand(dateTime, chatUser, chatUserManager);
                break;
            case "/chid":
                message = new RenameCommand(dateTime, chatUser, text.substring(6), chatUserManager);
                break;
            case "/exit":
                message = new ExitCommand(dateTime, chatUser, chatUserManager);
                break;
            default:
                message = null;
                break;
        }
        return message;
    }
}
