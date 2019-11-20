package echoserver;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocketCreatorTest {
    @Mock
    ServerSocket mockServerSocket;

    @Test
    public void testServerSocketGetsCreated() {
        try {
            assertNotNull(SocketCreator.createServerSocket(1234));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testServerSocketWithSpecificPortGetsCreated() {
        final int testPort = 9001;
        try {
            ServerSocket testServerSocket = SocketCreator.createServerSocket(testPort);
            assertEquals(testServerSocket.getLocalPort(), testPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClientSocketGetsCreated() {
        try {
            when(mockServerSocket.accept()).thenReturn(new Socket());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assertNotNull(SocketCreator.createClientSocket(mockServerSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
