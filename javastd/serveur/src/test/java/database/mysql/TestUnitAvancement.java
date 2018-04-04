package database.mysql;

import listeners.mappers.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestUnitAvancement extends MySQLDatabase {
    public TestUnitAvancement() {
        super();
    }

    private static final String pseudo = "TestAvancement";
    private static final String email = "avancement@test.fr";
    private static final String nom = "avancement";
    private static final String prenom = "test";
    private static final String password = "test";

    private static int id = 0;


    //DATABASE pour les test
    private static final String urlTEST = "jdbc:mysql://mysql-lpepd.alwaysdata.net/lpepd_test";
    private static final String userTEST = "lpepd_test";
    private static final String passwordTEST = "G3792WtYcXhs";
    private static final Connection connectionTEST = Connect.connection(urlTEST,userTEST,passwordTEST);

    private static final TestUnitPassword database = new TestUnitPassword();

    private static void setId(int id) {
        TestUnitAvancement.id = id;
    }

    @BeforeClass
    public static void begin() {
        Session session;
        if (database._existEmail(email, connectionTEST) == false) {
            database._addUser(pseudo, nom, prenom, email, password, 11, connectionTEST);
        }
        session = database._connection(email, password, connectionTEST);
        TestUnitAvancement.setId(session.getId());
    }


    @Test public void connection(){
        assertNotEquals(0,id);
    }

    @Test public void getExercice(){
        Hashtable hashtable = new Hashtable<Integer, String>();
        hashtable = database._getExerciceList(connectionTEST);
        System.out.println(hashtable.toString());

        assertTrue(hashtable.get(3).equals("exerciceTest1"));
        assertTrue(hashtable.get(4).equals("exerciceTest2"));
        assertTrue(hashtable.get(5).equals("exerciceTest3"));
    }


}
