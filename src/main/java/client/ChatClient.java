package client;

import java.io.*;
import java.net.ConnectException;
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


                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new BufferedOutputStream(
                                                connection.getOutputStream()))));

                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {

            initializeListenLogic(in);

            while (true) {
                String inputString = consoleInput.readLine();
                if (CommandValidator.validateMessage(inputString)) {
                    out.println(inputString);
                    out.flush();
                } else {
                    System.out.println("Unknown command!");
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            System.out.println("Error while connecting to chat server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeListenLogic(BufferedReader in) {
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println(in.readLine());
                }
            } catch (IOException e) {
                System.out.println("Error while receiving new message!");
                e.printStackTrace();
            }
        }).start();
    }
}
