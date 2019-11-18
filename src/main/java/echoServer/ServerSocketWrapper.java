package echoserver;

import java.io.*;

import java.net.Socket;
import java.net.ServerSocket;

import java.util.Scanner;

public class ServerSocketWrapper implements ServerSocketWrapperInterface {
    private Scanner input;
    private PrintWriter output;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public void createSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();

            InputStream inputToServer = clientSocket.getInputStream();
            OutputStream outputFromServer = clientSocket.getOutputStream();

            input = new Scanner(inputToServer, "UTF-8");
            output = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);

            output.println("Socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveData() {
        String inputData = input.nextLine();
        return inputData;
    }

    public void sendData(String data) {
        output.println("Echo: " + data);
    }

    public void close() {
        try {
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
