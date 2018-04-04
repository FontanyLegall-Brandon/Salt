package database.mysql;

import listeners.mappers.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUnitAvancement extends MySQLDatabase {
    public TestUnitAvancement() {
        super();
    }

    private static final String pseudo = "test";
    private static final String nom = "test";
    private static final String prenom = "test";
    private static final String email = "test@test.fr";
    private static final String password = "test";
    private static final int age = 11;
    private static int id = 0;


    //DATABASE pour les test
    private static final String urlTEST = "jdbc:mysql://mysql-lpepd.alwaysdata.net/lpepd_test";
    private static final String userTEST = "lpepd_test";
    private static final String passwordTEST = "G3792WtYcXhs";
    private static final Connection connectionTEST = Connect.connection(urlTEST,userTEST,passwordTEST);

    private static final TestUnitPassword database = new TestUnitPassword();

    @BeforeClass
    public static void begin(){

        if(database._existPseudo(pseudo,connectionTEST)==false){
            database._addUser(pseudo,nom,prenom,email,password,age,connectionTEST);
        }else{
            database._deleteUser(pseudo,connectionTEST);
            database._addUser(pseudo,nom,prenom,email,password,age,connectionTEST);
        }
    }

    @Test public void getExercice(){
        Hashtable hashtable = new Hashtable<Integer, String>();
        hashtable = database._getExerciceList(connectionTEST);
        System.out.println(hashtable.toString());

        assertTrue(hashtable.get(3).equals("exerciceTest1"));
        assertTrue(hashtable.get(4).equals("exerciceTest2"));
        assertTrue(hashtable.get(5).equals("exerciceTest3"));
    }



    @AfterClass
    public static void end(){

        if(database._existPseudo(pseudo,connectionTEST)){
            database._deleteUser(pseudo,connectionTEST);
        }
    }

}
