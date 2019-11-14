package echoserver;
public interface ClientSocketWrapperInterface {
    void createSocket(String ip, int port);
    String receiveData();
    void sendData(String data);
    void close();
}
