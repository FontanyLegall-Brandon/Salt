package database.mysql;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import database.Database;
import listeners.mappers.Session;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class TestUnitConnexion extends MySQLDatabase{

    private static final String pseudo = "test";
    private static final String nom = "test";
    private static final String prenom = "test";
    private static final String email = "test@test.fr";
    private static final String password = "test";
    private static final int age = 11;

    private static final String urlDB = "jdbc:mysql://mysql-lpepd.alwaysdata.net/lpepd_test";
    private static final String userDB = "lpepd_test";
    private static final String passwordDB = "G3792WtYcXhs";
    private static final Connection connection = Connect.connection(urlDB,userDB,passwordDB);


    @BeforeClass public static void begin(){
        Database database = new MySQLDatabase();

        if(database.existPseudo(pseudo)==false){
            database.addUser(pseudo,nom,prenom,email,password,age);
        }
    }

    @Test public void testConnection(){
        Database database = new MySQLDatabase();

        Session session;

        session = database.connection(email,password);


        assertEquals(pseudo,session.getPseudo());
        assertEquals(nom,session.getNom());
        assertEquals(prenom,session.getPrenom());
        assertEquals(email,session.getEmail());
        assertEquals(age,session.getAge());
    }


    @AfterClass public static void end(){
        Database database = new MySQLDatabase();

        if(database.existPseudo(pseudo)){
            database.deleteUser(pseudo);
        }
    }






}

