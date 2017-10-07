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

//            initializeListenLogic(in);

            while (true) {
                String inputString = consoleInput.readLine();
                out.println(inputString);
                out.flush();
                System.out.println(in.readLine());
            }

//            out.println("IT WORKS!!!!");
//            out.flush();
//
//            System.in.read();
//            System.out.println(in.readLine());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            System.out.println("Error while connecting to chat server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void initializeListenLogic(BufferedReader in) {
//        new Thread(() -> {
//            try {
//                System.out.println(in.readLine());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
