package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.* ;

/**
 * La classe Connect permet de se connecter à la base de donnée distant grace à la librairie JDBC.
 * Driver JDBC : com.mysql.jdbc.Driver
 */
public class Connect {

    //JDBC Driver
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //DATABASE classique
    private static final String DB_URL = "jdbc:mysql://mysql-lpepd.alwaysdata.net/lpepd_database";
    private static final String USER = "lpepd";
    private static final String PASS = "qt8i244AZUsA";

    /**
     * La fonction permet la connexion à la database
     * @return objet du type Connection qui va être passé à la classe Database
     */

    public static Connection connection(){
        return connection(DB_URL,USER,PASS);
    }

    public static Connection connection(String url,String user,String password){

        Connection conn = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected database successfully...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        return conn;
    }

}