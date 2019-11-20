package echoserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocketIOTest {
    @Mock
    Socket clientSocket;

    @Mock
    InputStream inputStream;

    @Mock
    OutputStream outputStream;

    @Test
    public void testSocketInputGetsCreated() {
        try {
            when(clientSocket.getInputStream()).thenReturn(inputStream);
            assertNotNull(SocketIO.createSocketReader(clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketOutputGetsCreated() {
        try {
            when(clientSocket.getOutputStream()).thenReturn(outputStream);
            assertNotNull(SocketIO.createSocketWriter(clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadClientStreamLine() {
        String inputString = "Hello";
        Scanner input = new Scanner(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())));

        assertEquals("Hello", SocketIO.readFromInputStream(input));
    }

    @Test
    public void testWriteClientStreamLine() {
        String inputString = "World";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outContent, true);

        SocketIO.writeToOutputStream(printWriter, inputString);

        assertEquals("World\n", outContent.toString());
    }
}
