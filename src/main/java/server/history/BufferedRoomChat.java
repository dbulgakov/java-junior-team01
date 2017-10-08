package server.history;

public class BufferedRoomChat {
    int countOfMessages;
    String buffer = "";

    public BufferedRoomChat(int countOfMessages, String buffer) {
        this.countOfMessages = countOfMessages;
        this.buffer = buffer;
    }
}
