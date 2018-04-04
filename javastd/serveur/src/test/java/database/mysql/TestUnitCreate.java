package database.mysql;


import org.junit.*;

import java.sql.Connection;

import static org.junit.Assert.*;

public class TestUnitCreate extends MySQLDatabase{
    public TestUnitCreate() {
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

    private static final TestUnitCreate database = new TestUnitCreate();

    @BeforeClass public static void begin(){

        if(database._existPseudo(pseudo,connectionTEST)){
            database._deleteUser(pseudo,connectionTEST);
        }
    }

    @Test public void creationUtilisateur(){


        boolean bool1;
        boolean bool2;

        bool1 = database._addUser(pseudo,nom,prenom,email,password,age,connectionTEST);

        bool2 = database._existPseudo(pseudo,connectionTEST);

        //System.out.println(bool1);
        //System.out.println(bool2);

        assertEquals(bool1,bool2);
    }


    @Test public void testBlocage(){


        boolean bool1;
        boolean bool2;

        bool1 = database._addUser(pseudo,nom,prenom,email,password,age,connectionTEST);

        //System.out.println(bool1);
        //System.out.println(bool2);

        assertFalse(bool1);
    }


    @AfterClass public static void end(){

        if(database._existPseudo(pseudo,connectionTEST)){
            database._deleteUser(pseudo,connectionTEST);
        }
    }


}
