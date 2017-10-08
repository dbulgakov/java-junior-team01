package server.commands.creator;

import server.ChatUser;
import server.ChatUserManager;
import server.commands.Command;
import server.commands.types.*;

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
        String commandPrefix = text.split(" ")[0];

        Command command;

        switch (commandPrefix) {
            case "/snd":
                command = new SenderCommand(dateTime, chatUser, text.substring(5), chatUserManager);
                break;
            case "/hist":
                command = new HistoryCommand(dateTime, chatUser, chatUserManager);
                break;
            case "/chid":
                command = new RenameCommand(dateTime, chatUser, text.substring(6), chatUserManager);
                break;
            case "/exit":
                command = new ExitCommand(dateTime, chatUser, chatUserManager);
                break;
            case "/chroom":
                command = new ChangeRoomCommand(dateTime,chatUser,chatUserManager,"room" + text.substring(7));
                break;
            default:
                command = null;
                break;
        }
        return command;
    }
}
