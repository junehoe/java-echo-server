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
    }

    public void run() {
        try {
            setServerInputOutput(clientSocket);
            output.println("Connected to server");
            echo();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void setServerInputOutput(Socket clientSocket) throws IOException {
        setServerIn(SocketIO.createSocketReader(clientSocket));
        setServerOut(SocketIO.createSocketWriter(clientSocket));
    }

    private void setServerIn(Scanner in) {
        input = in;
    }

    private void setServerOut(PrintWriter out) {
        output = out;
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
