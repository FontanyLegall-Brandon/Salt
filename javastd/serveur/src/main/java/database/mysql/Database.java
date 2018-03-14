package database.mysql;
import serveur.Server;
import serveur.Session;

import java.sql.* ;
import java.io.* ;

public class Database implements database.Database {
  
  //TODO Implementer cette classe qui permet de faire l'interface entre la  database et le serveur


    @Override
    public void addUser(String pseudo, String nom, String prenom, String email, String password, int age) {
        Connection con = Connect.connection();

        try {
        // Envoi d’un requête générique
        String sql =  "select * from Marins" ;
        Statement smt = con.createStatement() ;
        ResultSet rs = smt.executeQuery(sql) ;
        while (rs.next()) {
            System.out.println(rs.getString("nom")) ;
        }
    }  catch (Exception e) {
        System.exit(1);
    }
    }

    @Override
    public Session connection(String email, String password) {
        Connection con = Connect.connection();

        int id= Integer.parseInt(null);
        String pseudo = null;
        String prenom = null;
        String nom = null;
        int age = Integer.parseInt(null);

        try {
            // Envoi d’un requête générique
            String sql =  "select * from membre WHERE email=" + email;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                id = rs.getInt("id");
                pseudo = rs.getString("pseudo");
                prenom = rs.getString("prenom");
                nom = rs.getString("nom");
                age = rs.getInt("age");
            }
        }  catch (Exception e) {
            System.exit(1);
        }
        return new Session(id,pseudo,prenom,nom,email,age);
    }


}
