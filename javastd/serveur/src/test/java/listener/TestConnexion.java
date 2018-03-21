package listener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import serveur.Session;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestConnexion {

    @Mock
    Session session;


    @Before
    public void setup() {
/*        when(session.getAge()).thenReturn(10);
        when(session.getEmail()).thenReturn("toto@titi.fr");
        when(session.getId()).thenReturn(1);
        when(session.getPseudo()).thenReturn("toto");*/
    }

    @Test
    public void test() {
        System.out.println("todo");
    }


}
