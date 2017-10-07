package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    public static void main(String[] args) {
        try (
                Socket connection = new Socket("localhost", 9999);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        connection.getInputStream())));

                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(
                                        connection.getOutputStream())))
        ) {


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
