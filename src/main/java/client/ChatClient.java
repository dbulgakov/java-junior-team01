package client;

import client.validation.result.ValidationResult;
import client.validation.validator.CommandValidator;
import client.validation.validator.Validator;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
/** Класс служит для обработки клиентской части чата.
 * Обрабатывает входящие и исходящие сообщения
 * <b>hostName</> хост
 * <b>port</> порт сервера
 * <b>validator<b/>Обрабатывает корректность исходящего сообщения
 * @autor  Team-01
 * @version 1.1
 */
public class ChatClient {
    private final String hostName;
    private final int port;

    private final Validator<String> validator = new CommandValidator(Arrays.asList("/snd", "/hist", "/chid"));

    public ChatClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void start() {
        try (
                Socket connection = new Socket(hostName, port);

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
            System.out.println("Successfully connected to " + connection.getLocalAddress() + " on port " + connection.getPort());

            initializeListenLogic(in);

            while (true) {

                String inputString = consoleInput.readLine();
                ValidationResult validationResult = validator.validate(inputString);

                if (validationResult.isValid()) {
                    out.println(inputString);
                    out.flush();
                } else {
                    System.out.println(validationResult);
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


    private void initializeListenLogic(BufferedReader in) {
        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readLine();
                    if (message == null) {
                        System.out.println("Error! Server is down!");
                        System.exit(0);
                        break;
                    }

                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Error while receiving new message!");
                e.printStackTrace();
            }
        }).start();
    }
}
