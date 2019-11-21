package echoserver;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer implements Runnable {
    private final String QUIT = "quit";
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

    public void setServerIn(Scanner in) {
        input = in;
    }

    public void setServerOut(PrintWriter out) {
        output = out;
    }

    public void echo() {
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
