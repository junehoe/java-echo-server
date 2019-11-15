/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package echoserver;

public class Client {
    private ClientSocketWrapperInterface clientSocketWrapper;

    public Client(ClientSocketWrapperInterface clientSocketWrapper) {
        this.clientSocketWrapper = clientSocketWrapper;
    }
    public void start(String ip, int port) {
        clientSocketWrapper.createSocket(ip, port);
        String input = clientSocketWrapper.receiveData();
        clientSocketWrapper.sendData(input);
        clientSocketWrapper.close();
    }
}