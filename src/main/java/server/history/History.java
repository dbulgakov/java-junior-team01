package server.history;

import server.messages.types.Message;

import java.io.*;

public class History {

    private BufferedReader in;
    private FileWriter out;
    private File path;
    private final String SYS_SEP = File.separator;
    private final String FILE_ADDRESS = "src" + SYS_SEP + "main" + SYS_SEP + "java" + SYS_SEP + "server";

    public History() {

        path = new File(FILE_ADDRESS, "history.txt");
        try {

            out = new FileWriter(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    *  Add message to file history
    *  @para
    * **/
    public void addMessage(Message message) {
        try {
            out = new FileWriter(path, true);
            String msg = message.getText() + System.lineSeparator();
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

    public String getHistory() {
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
