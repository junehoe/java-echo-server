package echoserver;
import java.net.*;
import java.io.*;

public class MockServerSocketWrapper implements ServerSocketWrapperInterface {
    private BufferedReader input;
    private PrintWriter output;
    private boolean createSocketCalled = false;
    private String sentData;
    private boolean closeCalled = false;

    public MockServerSocketWrapper(BufferedReader input, PrintWriter out) {
        this.input = input;
        this.output = output;
    }

    public void createSocket(int port) {
        createSocketCalled = true;
    }

    public String receiveData() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Did not receive data";
        }
    }

    public void sendData(String data) {
        sentData = data;
        //sentData = output.println(data);
    }

    public void close() {
        closeCalled = true;
    }

    public boolean wascreateSocketCalled() {
        return createSocketCalled;
    }

    public String getSentData() {
        return sentData;
    }

    public boolean wasCloseCalled() {
        return closeCalled;
    }
}
