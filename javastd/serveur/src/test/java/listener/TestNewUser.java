package listener;

import com.corundumstudio.socketio.SocketIOClient;
import database.Database;
import listeners.NewUserListener;
import listeners.mappers.Reply;
import listeners.mappers.UserInfo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import serveur.Server;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestNewUser {

    private UserInfo infos1, infos2, infos3;

    Reply reponseOk, reponsePb, reponseExiste;

    private NewUserListener listenerTest;

    @Mock
    private Database bdd;

    @Mock
    private Server serveur;

    @Mock
    private SocketIOClient socketClient;
    
    @BeforeClass
    public static void setUpClass() {

        System.out.println("***********************************************************");
    }

    @Before
    public void setUp() {
    	
    	/*
    	 * ******************************
    	 * *	Stub des Mock Objects	*
    	 * ******************************
    	 */
    	
    	// Base de donnée
        when(bdd.addUser(any(), any(), any(), any(), any(), anyInt())).thenReturn(true);		// L'ajout marche toujours
        when(bdd.addUser(eq("titi"), any(), any(), any(), any(), anyInt())).thenReturn(false);	// Sauf pour le pseudo titi
        when(bdd.existUser(anyString())).thenReturn(false);
        when(bdd.existUser("toto")).thenReturn(true);			// Encore une fois ici toto est un pseudo existant
        
        // Serveur
        when(serveur.getDatabase()).thenReturn(bdd);    // Permet d'injecter la mock db dans le mock server…

        // Messages JSon touts faits pour tester le comportement de onData dessus
        infos1 = new UserInfo("toto", "toto@titi.fr", "Toto", "Charles-Henri", "mdpcomplique", 47);
        infos2 = new UserInfo("tata", "tata@titi.fr", "Tata", "Marie-Henriette", "mdpcomplique2", 74);
        infos3 = new UserInfo("titi", null, null, null, null, 0);
        
        //	Réponses attendues 
        reponseOk = new Reply("signedUp");
        reponsePb = new Reply("signUpFailure");
        reponseExiste = new Reply("userAlreadyExists");
        
        // Instanciation du listener
        listenerTest = new NewUserListener(serveur);

    }


    @Test
    public void testOnDataValidUser() {

        listenerTest.onData(socketClient, infos2, null);    // Appel de onData sur infos1, l'utilisateur n'existe pas

        // On vérifie qu'on a bien demandé à la BDD d'ajouter l'utilisateur
        verify(bdd).addUser(eq(infos2.getPseudo()), anyString(), anyString(), eq(infos2.getEmail()), anyString(), anyInt());
        
        // vérifie qu'on envoi un retour au client, avec une réponse positive
        verify(socketClient).sendEvent(eq("signUpReply"), refEq(reponseOk));


    }

    @Test
    public void testOnDataInvalidUser() {

        // Appel de onData cette fois sur un utilisateur existant dans la bdd
        listenerTest.onData(socketClient, infos1, null);

        // Vérifie qu'on a jamais appelé la méthode addUser : l'utilisateur existe déjà
        verify(bdd, never()).addUser(any(), any(), any(), any(), any(), anyInt());

        // Vérifie qu'on a bien un message de retour au client
        verify(socketClient).sendEvent(eq("signUpReply"), refEq(reponseExiste));
    }

    @Test
    public void testOnDataFailure() {

        // Appel de onData, sur un UserInfo avec le pseudo "titi", la BDD doit renvoyer false pour une raison inconnue
        listenerTest.onData(socketClient, infos3, null);

        // On vérifie qu'on a bien demandé à la BDD d'ajouter l'utilisateur
        // (cette fois on ne vérifie pas le détail du type de ce qu'on
        verify(bdd).addUser(eq(infos3.getPseudo()), any(), any(), eq(infos3.getEmail()), any(), anyInt());

        // Vérifie qu'on a bien un message de retour avec une erreur  au client
        verify(socketClient).sendEvent(eq("signUpReply"), refEq(reponsePb));

    }
}
