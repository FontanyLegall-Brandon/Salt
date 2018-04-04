package database.mysql;

import com.sun.source.tree.AssertTree;
import listeners.mappers.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import serveur.Avancement;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import static org.junit.Assert.*;

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


    //Avancements
    private static final Avancement avancement1 = new Avancement(3,2,100);
    private static final Avancement avancement2 = new Avancement(3,1,100);
    private static final Avancement avancement3 = new Avancement(4,1,50);
    private static final Avancement avancement4 = new Avancement(3,3,50);

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
        //System.out.println(hashtable.toString());

        assertTrue(hashtable.get(3).equals("exerciceTest1"));
        assertTrue(hashtable.get(4).equals("exerciceTest2"));
        assertTrue(hashtable.get(5).equals("exerciceTest3"));
    }

    @Test public void getAvancement() {

        HashSet hashSet;
        hashSet = database._getUserAvancement(TestUnitAvancement.id,connectionTEST);
        /*
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Avancement avancement = Avancement.class.cast(it.next());
            System.out.println(avancement.getExerciceId());
            System.out.println(avancement.getLevelId());
            System.out.println(avancement.getPourcentage());
            System.out.println("\n");

        }
        */
        assertTrue(hashSet.contains(avancement1));
        assertTrue(hashSet.contains(avancement2));
        assertTrue(hashSet.contains(avancement3));
        assertTrue(hashSet.contains(avancement4));
    }

    @Test public void getAvancementOf() {
        HashSet hashSet = new HashSet<Avancement>();
        hashSet = database._getUserAvancementOf(TestUnitAvancement.id, avancement3.getExerciceId(),connectionTEST);
        /*
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Avancement avancement = Avancement.class.cast(it.next());
            System.out.println(avancement.getExerciceId());
            System.out.println(avancement.getLevelId());
            System.out.println(avancement.getPourcentage());
            System.out.println("\n");
        }
        */

        assertTrue(hashSet.contains(avancement3));

        assertFalse(hashSet.contains(avancement1));
        assertFalse(hashSet.contains(avancement2));
        assertFalse(hashSet.contains(avancement4));
    }

    @Test public void getAvancementOfAt(){
        int pourcentage;
       pourcentage = database._getUserAvancementOfAt(TestUnitAvancement.id,avancement1.getExerciceId(),avancement1.getLevelId(),connectionTEST);
        assertEquals(avancement1.getPourcentage(),pourcentage);

        int pourcentage2;
        pourcentage = database._getUserAvancementOfAt(TestUnitAvancement.id,avancement2.getExerciceId(),avancement2.getLevelId(),connectionTEST);
        assertEquals(avancement2.getPourcentage(),pourcentage);

        int pourcentage3;
        pourcentage = database._getUserAvancementOfAt(TestUnitAvancement.id,avancement3.getExerciceId(),avancement3.getLevelId(),connectionTEST);
        assertEquals(avancement3.getPourcentage(),pourcentage);

        int pourcentage4;
        pourcentage = database._getUserAvancementOfAt(TestUnitAvancement.id,avancement4.getExerciceId(),avancement4.getLevelId(),connectionTEST);
        assertEquals(avancement4.getPourcentage(),pourcentage);
    }


}
