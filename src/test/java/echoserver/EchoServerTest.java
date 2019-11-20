package echoserver;

import java.io.*;

import java.net.Socket;

import java.util.Scanner;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.mockito.Mock;

public class EchoServerTest {
    private EchoServer server;

    @Mock
    Socket clientSocket;

    @Before
    public void initialize() {
        server = new EchoServer(clientSocket);
    }

    @Test
    public void testServerCanEchoMessage() {
        String inputString = "This is a test message\nquit\n";
        server.setServerIn(new Scanner(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes()))));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outContent, true);
        server.setServerOut(printWriter);

        server.echo();

        assertEquals("This is a test message\n", outContent.toString());
    }
}
