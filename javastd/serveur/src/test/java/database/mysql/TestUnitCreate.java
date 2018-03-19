package database.mysql;


import org.junit.*;

import static org.junit.Assert.*;

public class TestUnitCreate {

    private static final String pseudo = "test";
    private static final String nom = "test";
    private static final String prenom = "test";
    private static final String email = "test@test.fr";
    private static final String password = "test";
    private static final int age = 11;

    @BeforeClass public static void begin(){
        database.mysql.Database database = new Database();

        if(database.existUser(pseudo)){
            database.deleteUser(pseudo);
        }
    }

    @Test public void creationUtilisateur(){
        database.mysql.Database database = new Database();


        boolean bool1;
        boolean bool2;

        bool1 = database.addUser(pseudo,nom,prenom,email,password,age);

        bool2 = database.existUser(pseudo);

        System.out.println(bool1);
        System.out.println(bool2);

        assertEquals(bool1,bool2);
    }


    @AfterClass public static void end(){
        database.mysql.Database database = new Database();

        if(database.existUser(pseudo)){
            database.deleteUser(pseudo);
        }
    }


}
