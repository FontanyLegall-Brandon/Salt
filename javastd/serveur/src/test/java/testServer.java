import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.protocol.Packet;
import org.junit.Test;
import org.junit.Before;


import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class testServer {

    @Test
    public void testConnexion() {
        Server server = new Server("localhost", 1345);
        server.run();
        assertTrue(server.isRunning());//vérifie que le serveur tourne lorsqu'il est lancé
        server.stop();
        assertFalse(server.isRunning());//vérifie que le serveur s'est bien arrêté
    }
}
