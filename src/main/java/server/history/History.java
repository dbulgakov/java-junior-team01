package server.history;

import server.commands.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class History {

    private BufferedReader in;
    private FileWriter out;
    private File path;
    Map buffers = new HashMap< String, BufferedRoomChat>();
    private final String SYS_SEP = File.separator;
    private final String FILE_ADDRESS = "src" + SYS_SEP +
                                            "main" + SYS_SEP +
                                                "java" + SYS_SEP +
                                                    "server" + SYS_SEP
                                                        + "history";

    /*
    *  Add command to file history
    *  @param command
    * **/
    public void addMessage(Command command, String roomName) {
        String fileName = roomName + "history.txt";
        path = new File(FILE_ADDRESS, fileName);
        try {
            out = new FileWriter(path, true);
            String msg = command.getText() + System.lineSeparator();
            out.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    *  return all history
    **/
    public String getHistory(String roomName) {
        String fileName = roomName + "history.txt";
        path = new File(FILE_ADDRESS, fileName);
        if (!path.exists()) return "";
        String line;
        String outputString = "";
        try {
            in = new BufferedReader(new InputStreamReader
                    (new BufferedInputStream
                            (new FileInputStream(path))));
            while ((line = in.readLine()) != null)
                outputString += line + System.lineSeparator();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputString;
    }
}
