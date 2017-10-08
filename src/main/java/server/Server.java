package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
/** Класс обрабатывает серверную часть
 * @autor  Team-01
 * @version 1.1
 */
public class Server {
    private static List<BufferedWriter> clientsOuts;

    public static void main(String[] args) {
        clientsOuts = new ArrayList<>();
        serverLoop(Server::clientLoop);
    }

    private static void serverLoop(Consumer<Socket> toDo) {
        try (ServerSocket listener
                     = new ServerSocket(9999)) {

            while (true) {
                Socket client = listener.accept();
                new Thread(() -> toDo.accept(client)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clientLoop(Socket client) {
        try (
                BufferedWriter out = (new BufferedWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(
                                        client.getOutputStream()))));

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        client.getInputStream())));
        ) {
            clientsOuts.add(out);
            while (true) {
                String message = in.readLine();
                if (message == null) break ;
                for (BufferedWriter currentOut :clientsOuts
                     ) {
                    synchronized (clientsOuts) {
                        currentOut.write(message);
                        currentOut.newLine();
                        currentOut.flush();
                    }
                }
            }
            clientsOuts.remove(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
