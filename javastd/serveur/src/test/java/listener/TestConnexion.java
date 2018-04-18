package listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.corundumstudio.socketio.SocketIOClient;

import listeners.IdentificationListener;
import database.Database;
import mappers.LoginInfos;
import mappers.Session;
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
	
	private LoginInfos infosLoginCorrect, infosMauvaisMdp;

    @Before
    public void setup() {
    	
    	session1 = new Session(1, "Toto", "Charles-Henri", "Michel", "toto@titi.fr", 59);
    	//session2 = new Session(2, "Tata", "Jean-Marie", "Martin", "tata@titi.fr", 68);
    	sessionVide = new Session(0, null, null, null, null, 0);
    	
    	infosLoginCorrect = new LoginInfos("toto@titi.fr", "mdp");
    	infosMauvaisMdp = new LoginInfos("toto@titi.fr", "mauvaisMdp");
    	
    	when(serveur.getDatabase()).thenReturn(database);	// Le mock serveur contient maintenant notre mock database

		// Quand on se connecte avec l'email toto avec n'importe quel mdp on renvoit une session vide (mauvais login)
    	when(database.connection(eq("toto@titi.fr"), anyString())).thenReturn(sessionVide);
    	// mais si l'on a le bon mdp on renvoit la session1
    	when(database.connection(eq("toto@titi.fr"), eq("mdp"))).thenReturn(session1);

    	// Instanciation du listener que l'on va tester
    	listenerTest = new IdentificationListener(serveur);
		
    }

    @Test
    public void workingLogin() {

        // On appel le onData du listener avec un couple login mdp auquel la bd répond positivement
    	listenerTest.onData(socketClient, infosLoginCorrect, null);
    	
    	verify(socketClient).sendEvent(eq("session"), refEq(session1)); // Vérifie qu'on renvoit une session
    	
    }


    @Test
    public void incorrectLogin() {

        // Appel de onData avec un mdp considéré par la bd comme mauvais
        listenerTest.onData(socketClient, infosMauvaisMdp, null);

        // Vérifie que le message de retour indique que le login est incorrect
        verify(socketClient).sendEvent(eq("badCredentials"), any());

    }

}
