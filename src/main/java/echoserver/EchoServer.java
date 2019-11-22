package echoserver;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer implements Runnable {
    private Socket clientSocket;
    private InputValidator inputValidator;
    private Scanner input;
    private PrintWriter output;

    public EchoServer(Socket clientSocket, InputValidator inputValidator) {
        this.clientSocket = clientSocket;
        this.inputValidator = inputValidator;
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
            if (inputValidator.isQuit(inputString)) {
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
