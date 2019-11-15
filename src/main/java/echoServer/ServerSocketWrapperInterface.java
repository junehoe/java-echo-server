package echoserver;

public interface ServerSocketWrapperInterface {
    void createSocket(int port);
    String receiveData();
    void sendData(String data);
    void close();
}
