package echoserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.lang.Thread;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Port number was not specified");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

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