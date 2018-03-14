package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public static Connection connection(){
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
    }

    public static void main(String[] args) {

        Connect.connection();
    }
}