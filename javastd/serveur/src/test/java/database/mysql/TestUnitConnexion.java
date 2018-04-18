package database.mysql;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import mappers.Session;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class TestUnitConnexion extends MySQLDatabase{
    public TestUnitConnexion() {
        super();
    }

    private static final String pseudo = "test";
    private static final String nom = "test";
    private static final String prenom = "test";
    private static final String email = "test@test.fr";
    private static final String password = "test";
    private static final int age = 11;


    //DATABASE pour les test
    private static final String urlTEST = "jdbc:mysql://mysql-lpepd.alwaysdata.net/lpepd_test";
    private static final String userTEST = "lpepd_test";
    private static final String passwordTEST = "G3792WtYcXhs";
    private static final Connection connectionTEST = Connect.connection(urlTEST,userTEST,passwordTEST);

    private static final TestUnitConnexion database = new TestUnitConnexion();


    @BeforeClass public static void begin(){


        if(database._existPseudo(pseudo,connectionTEST)==false){
            database._addUser(pseudo,nom,prenom,email,password,age,connectionTEST);
        }
    }

    @Test public void testConnection(){


        Session session;

        session = database._connection(email,password,connectionTEST);


        assertEquals(pseudo,session.getPseudo());
        assertEquals(nom,session.getNom());
        assertEquals(prenom,session.getPrenom());
        assertEquals(email,session.getEmail());
        assertEquals(age,session.getAge());
    }



    @AfterClass public static void end(){


        if(database._existPseudo(pseudo,connectionTEST)){
            database._deleteUser(pseudo,connectionTEST);
        }
    }
}

