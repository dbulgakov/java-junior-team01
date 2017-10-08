package server.history;

import server.commands.Command;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class History {
    private File path;
    Map< String, BufferedRoomChat> buffers = new HashMap< String, BufferedRoomChat>();
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
        String message = command.getText() + System.lineSeparator();
        if (buffers.containsKey(roomName)) {
            buffers.get(roomName).buffer += System.lineSeparator() + message;
            buffers.get(roomName).countOfMessages++;
        } else {
            buffers.put(roomName, new BufferedRoomChat(1, message));
        }
        checkBuffer(roomName);
    }

    public void flushAllBuffer(){
        for (String roomName : buffers.keySet()) {
            flushBuffer(roomName);
        }
    }

    /*
    *  return all history
    **/
    public String getHistory(String roomName) {
        String fileName = roomName + "history.txt";
        path = new File(FILE_ADDRESS, fileName);
        String outputString = "";
        if (path.exists()) {
            String line;
            try (BufferedReader in = new BufferedReader(new InputStreamReader
                    (new BufferedInputStream
                        (new FileInputStream(path))));){
                while ((line = in.readLine()) != null) {
                    outputString += line + System.lineSeparator();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (buffers.containsKey(roomName)){
            outputString += buffers.get(roomName).buffer;
        }
        return outputString;
    }

    private void checkBuffer(String roomName){
        if (buffers.get(roomName).countOfMessages >= 1000) {
            flushBuffer(roomName);
        }
    }

    private void flushBuffer(String roomName){
        String fileName = roomName + "history.txt";
        path = new File(FILE_ADDRESS, fileName);
        try(FileWriter out = new FileWriter(path, true);) {
            out.write(buffers.get(roomName).buffer);
            buffers.get(roomName).countOfMessages = 0;
            buffers.get(roomName).buffer = "";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
