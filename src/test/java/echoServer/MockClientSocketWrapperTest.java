package echoserver;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class MockClientWrapperTest {
    MockClientSocketWrapper mockClientSocketWrapper;

    @Before
    public void initialize() {
        String ip = "localhost";
        int port = 4242;
        BufferedReader input = new BufferedReader(new StringReader("hiii\n"));
        PrintWriter output = new PrintWriter(new StringWriter(), true);
        mockClientSocketWrapper = new MockClientSocketWrapper(input, output);
        Client client = new Client(mockClientSocketWrapper);
        client.start(ip, port);
        mockClientSocketWrapper.close();
    }

    @Test
    public void connectsToServerSocket() {
        assertTrue(mockClientSocketWrapper.createSocketWasCalled());
    }

    @Test
    public void dataIsSentToServerSocket() {
        assertEquals("hiii", mockClientSocketWrapper.getSentData());
    }

    @Test
    public void socketCloses() {
        assertTrue(mockClientSocketWrapper.closeWasCalled());
    }
}
