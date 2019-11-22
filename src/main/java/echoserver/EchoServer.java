package echoserver;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer implements Runnable {
    private Scanner input;
    private PrintWriter output;
    private Socket clientSocket;

    public EchoServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.input = SocketIO.createSocketReader(clientSocket);
            this.output = SocketIO.createSocketWriter(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        output.println("Connected to server");
        echo();
    }

    private void echo() {
        String inputString;
        while ((inputString = SocketIO.readFromInputStream(input)) != null) {
            if (InputValidator.isQuit(inputString)) {
                close();
                break;
            }
            SocketIO.writeToOutputStream(output, inputString);
        }
    }

    private void close() {
        try {
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
