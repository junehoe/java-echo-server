package echoserver;

import java.io.*;

import java.net.ServerSocket;
import java.net.InetAddress;
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
        EchoServer server = new EchoServer(clientSocket, new InputValidator());

        server.run();

        assertEquals("Connected to server\nThis is a test message\n", outContent.toString());
    }

    @Test
    public void testCloseMethodGetsCalledWhenQuitIsEntered() throws IOException {
        String inputString = "quit\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        EchoServer server = new EchoServer(clientSocket, new InputValidator());

        server.run();

        verify(clientSocket, times(1)).close();
    }

    @Test
    public void testServerReceivesMessagesFromClient() throws IOException {
        InetAddress ip = InetAddress.getByName("1.2.3.4");
        String inputString = "This is a best message\nquit\n";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream dummyOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        when(clientSocket.getOutputStream()).thenReturn(dummyOutContent);
        when(clientSocket.getInetAddress()).thenReturn(ip);
        EchoServer server = new EchoServer(clientSocket, new InputValidator());

        server.run();

        assertEquals("/1.2.3.4: This is a best message\n", outContent.toString());

        System.setOut(System.out);
    }
}
