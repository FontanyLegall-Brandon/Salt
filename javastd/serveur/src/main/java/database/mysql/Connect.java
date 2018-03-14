package database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://mysql.hostinger.fr/u606391292_lpepd";
            String user = "u606391292_root0";
            String passwd = "tauub>=WkhAUG3S~h";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            System.out.println("Connexion effective !");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}