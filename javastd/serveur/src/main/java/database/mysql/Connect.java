package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.* ;

public class Connect {

    /*
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://mysql.hostinger.fr/u606391292_lpepd";

    //  Database credentials
    private static final String USER = "u606391292_root0";
    private static final String PASS = "tauub>=WkhAUG3S~h";
    */

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://mysql-lpepd.alwaysdata.net";

    //  Database credentials
    private static final String USER = "lpepd";
    private static final String PASS = "qt8i244AZUsA";


    public static Connection connection(){

        Connection conn = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        return conn;


        /*
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://mysql.hostinger.fr/u606391292_lpepd";
            String user = "u606391292_root0";
            String passwd = "tauub>=WkhAUG3S~h";

            conn = DriverManager.getConnection(url, user, passwd);

            System.out.println("Connexion effective !");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return conn;
        */
    }

    public static void main(String[] args) {

        Connect.connection();
    }
}