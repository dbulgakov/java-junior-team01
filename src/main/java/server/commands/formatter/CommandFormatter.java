package server.commands.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class CommandFormatter {
    private CommandFormatter() {
    }


    public static String format(String name, LocalDateTime dateTime, String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = formatter.format(dateTime);
        return name + " " + formattedDateTime + " " + text;
    }
}
