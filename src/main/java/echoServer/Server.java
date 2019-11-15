package echoserver;

public class Server {
    private ServerSocketWrapperInterface socketWrapper;

    public Server(ServerSocketWrapperInterface socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(int port) {
        socketWrapper.createSocket(port);
        String input = socketWrapper.receiveData();
        socketWrapper.sendData(input);
        socketWrapper.close();
    }
}