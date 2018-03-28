package listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.corundumstudio.socketio.SocketIOClient;

import listeners.IdentificationListener;
import database.Database;
import listeners.mappers.LoginInfos;
import listeners.mappers.Session;
import serveur.Server;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestConnexion {
	
	private IdentificationListener listenerTest;

	@Mock
	private Server serveur; // Création d'un mock serveur
	
	@Mock
	private Database database;
	
	@Mock
	private SocketIOClient socketClient; 
	
	private Session session1, session2, sessionVide;
	
	private LoginInfos infos1, infos2;

    @Before
    public void setup() {
    	
    	session1 = new Session(1, "Toto", "Charles-Henri", "Michel", "toto@titi.fr", 59);
    	//session2 = new Session(2, "Tata", "Jean-Marie", "Martin", "tata@titi.fr", 68);
    	//sessionVide = new Session(0, null, null, null, null, 0);
    	
    	infos1 = new LoginInfos("toto@titi.fr", "mdp");
    	infos2 = new LoginInfos("toto@titi.fr", "mauvaisMdp");
    	
    	when(serveur.getDatabase()).thenReturn(database);
    	
    	when(database.connection(eq("toto@titi.fr"), anyString())).thenReturn(sessionVide);
    	when(database.connection(eq("toto@titi.fr"), eq("mdp"))).thenReturn(session1);
    	
    	listenerTest = new IdentificationListener(serveur);
		
    }

    @Test
    public void testWorkingLogin() {
    	
    	listenerTest.onData(socketClient, infos1, null);
    	
    	verify(socketClient).sendEvent(eq("session"), refEq(session1));
    	
    }


}
