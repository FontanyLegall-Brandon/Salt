package database.mysql;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import serveur.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUnitPassword {
    private static final String pseudo = "test";
    private static final String nom = "test";
    private static final String prenom = "test";
    private static final String email = "test@test.fr";
    private static final String password = "test";
    private static final int age = 11;
    private static int id = 0;

    private static final String newpassword = "test2";

    private static void setId(int id) {
        TestUnitPassword.id = id;
    }

    @BeforeClass
    public static void begin(){
        database.mysql.MySQLDatabase database = new MySQLDatabase();

        if(database.existUser(pseudo)==false){
            database.addUser(pseudo,nom,prenom,email,password,age);
        }
    }

    @Test public void testConnection(){
        MySQLDatabase database = new MySQLDatabase();

        Session session;

        session = database.connection(email,password);


        assertEquals(pseudo,session.getPseudo());
        assertEquals(nom,session.getNom());
        assertEquals(prenom,session.getPrenom());
        assertEquals(email,session.getEmail());
        assertEquals(age,session.getAge());

        setId(session.getId());
    }

    @Test public void editPassword(){
        MySQLDatabase database = new MySQLDatabase();

        boolean bool;

        bool = database.editPassword(id,password,newpassword,newpassword);

        assertTrue(bool);
    }

    @Test public void nextConnection(){
        MySQLDatabase database = new MySQLDatabase();

        Session session;

        session = database.connection(email,newpassword);


        assertEquals(pseudo,session.getPseudo());
        assertEquals(nom,session.getNom());
        assertEquals(prenom,session.getPrenom());
        assertEquals(email,session.getEmail());
        assertEquals(age,session.getAge());
    }


    @AfterClass public static void end(){
        database.mysql.MySQLDatabase database = new MySQLDatabase();

        if(database.existUser(pseudo)){
            database.deleteUser(pseudo);
        }
    }







}