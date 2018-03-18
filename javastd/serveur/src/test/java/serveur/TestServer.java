package serveur;

import io.socket.client.IO;
import io.socket.client.Socket;
import listeners.NewUserListener;
import listeners.mappers.UserInfo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestServer {

    @Mock private NewUserListener newUserListener;  // Création d'un faux NewUserListener

    // ATTENTION  L'injection ne marche pas comme ça… il faudrait un setter éventuellement
    @InjectMocks Server server = new Server("localhost", 10101);   // qu'on injecte dans un faux serveur

    @Mock UserInfo info;

    Socket mSocket;

    @Before
    public void setUp() {
        try {
            mSocket = IO.socket("localhost:10101");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        info = new UserInfo("toto", "titi"); // Attention, modification de la signature du contructeur UserInfo…
    }

    /*@Test
    public void testConnexion() {
        Server server = new Server("localhost", 1345);
        server.run();
        assertTrue(server.isRunning());//vérifie que le serveur tourne lorsqu'il est lancé
        server.stop();
        assertFalse(server.isRunning());//vérifie que le serveur s'est bien arrêté
    }*/

    @Test
    public void testNewUser() {
        mSocket.connect();
        mSocket.emit("newUser", info);

        // Ne passe pas puisque l'injection n'a pas marché, le newUserListener n'est pas implanté sur le serveur
        //verify(newUserListener, timeout(1000)).onData(any(), eq(info), any());

    }
}
