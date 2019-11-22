package echoserver;

import java.io.*;

import java.net.Socket;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EchoServerTest {

    @Mock
    Socket clientSocket;

    @Test
    public void testServerCanEchoMessage() throws IOException {
        String inputString = "This is a test message\nquit\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        EchoServer server = new EchoServer(clientSocket);

        server.run();

        assertEquals("Connected to server\nThis is a test message\n", outContent.toString());
    }

    @Test
    public void testCloseMethodGetsCalledWhenQuitIsEntered() throws IOException {
        String inputString = "quit\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        EchoServer server = new EchoServer(clientSocket);

        server.run();

        verify(clientSocket, times(1)).close();
    }
}
