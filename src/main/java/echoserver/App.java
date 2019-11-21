package echoserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.lang.Thread;

public class App {
    private static int port = 4242;

    public static void main(String[] args) {
        if (args.length >= 1 && InputValidator.isValidPort(args[0])) {
            port = Integer.parseInt(args[0]);
        } else {
            System.out.println("Invalid port provided. Defaulting to port 4242");
        }

        try {
            ServerSocket serverSocket = SocketCreator.createServerSocket(port);
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = SocketCreator.createClientSocket(serverSocket);
                Thread echoThread = new Thread(new EchoServer(clientSocket));
                echoThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}