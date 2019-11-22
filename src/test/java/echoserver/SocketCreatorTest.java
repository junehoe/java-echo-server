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
    public void testServerSocketGetsCreated() throws IOException {
        assertNotNull(SocketCreator.createServerSocket(1234));
    }

    @Test
    public void testServerSocketWithSpecificPortGetsCreated() throws IOException {
        final int testPort = 9001;
        ServerSocket testServerSocket = SocketCreator.createServerSocket(testPort);

        assertEquals(testServerSocket.getLocalPort(), testPort);
    }

    @Test
    public void testClientSocketGetsCreated() throws IOException {
        when(mockServerSocket.accept()).thenReturn(new Socket());

        assertNotNull(SocketCreator.createClientSocket(mockServerSocket));
    }
}
