package echoserver;

import java.net.*;
import java.io.*;

public class MockClientSocketWrapper implements ClientSocketWrapperInterface {
    private BufferedReader input;
    private PrintWriter output;
    private boolean socketCreated = false;
    private boolean closeCalled = false;
    private String sentData;

    public MockClientSocketWrapper(BufferedReader input, PrintWriter out) {
      this.input = input;
      this.output = output;
  }

    public void createSocket(String ip, int port) {
        socketCreated = true;
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
    }

    public void close() {
        closeCalled = true;
    }

    public boolean createSocketWasCalled() {
        return socketCreated;
    }

    public String getSentData() {
      return sentData;
    }

    public boolean closeWasCalled() {
        return closeCalled;
    }
}
