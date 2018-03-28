package database.mysql;
import database.Database;
import listeners.mappers.UserInfo;
import serveur.Avancement;
import serveur.Session;
import sun.security.x509.AVA;

import java.sql.* ;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * La classe Database implemente l'interface database avec toutes les commandes mysql necessaires
 */
public class MySQLDatabase implements database.Database {
  
  //TODO Implementer cette classe qui permet de faire l'interface entre la  database et le serveur



    /**
     * AddUser permet d'ajouter un nouvel utilisateur avec notamment :
     * <ul>
     *     <li>son identifiant(id) <strong>unique</strong></li>
     *     <li>son pseudo <strong>unique</strong></li>
     *     <li>son nom</li>
     *     <li>son prenom</li>
     *     <li>son email <strong>unique</strong></li>
     *     <li>son age</li>
     * </ul>
     *
     * @param pseudo le pseudo du compte
     * @param nom le nom de la personne ayant créé le compte
     * @param prenom le prenom de la personne ayant créé le compte
     * @param email l'email du compte
     * @param age l'age de la personne ayant créé le compte
     * @return un Boolean qui permet de savoir si l'ajout a bien été fait
     */
    @Override
    public Boolean addUser(String pseudo, String nom, String prenom, String email, String password, int age) {
        Connection con = Connect.connection();
        return _addUser(pseudo,nom,prenom,email,password,age,con);
    }

    protected Boolean _addUser(String pseudo, String nom, String prenom, String email, String password, int age,Connection con) {

        Boolean bool = false;

        try{
            // create our java preparedstatement using a sql update query
            PreparedStatement ps = con.prepareStatement("INSERT INTO membre(pseudo,prenom,nom,email,password,age) VALUES(?,?,?,?,?,?)");

            // set the preparedstatement parameters
            ps.setString(1,pseudo);
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setString(4,email);
            ps.setString(5,password);
            ps.setInt(6,age);

            // call executeUpdate to execute our sql update statement
            int exec = ps.executeUpdate();
            ps.close();

            bool = (exec == 1);
        }
        catch (SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return bool;
    }

    /**
     * connection permet à un utilisateur de se connecter
     *
     * @param email son email
     * @param password son mot de passe en sha1
     * @return un objet du type Session
     */
    @Override
    public Session connection(String email, String password) {
        Connection con = Connect.connection();
        return _connection(email,password,con);
    }


    protected Session _connection(String email, String password,Connection con){


        int id= 0;
        String pseudo = null;
        String prenom = null;
        String nom = null;
        int age = 0;

        try {
            // Envoi d’un requête générique
            String sql =  "select * from membre WHERE email='"+email+"'";
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


    /**
     * editPassword permet à un utilisateur de changer son password.
     * Rappel : C'est le client qui doit l'envoyé en sha1
     *
     * @param id l'id de l'utilisateur
     * @param old son ancien mot de passe
     * @param password le nouveau mot de passe
     * @param passwordVerification la verification du nouveau mot de passe
     * @return un boolean qui permet de dire si le mot de passe a bien été modifié
     */
    @Override
    public Boolean editPassword(int id, String old, String password,String passwordVerification) {
        Connection con = Connect.connection();
        return _editPassword(id, old, password, passwordVerification,con);
    }

    protected Boolean _editPassword(int id, String old, String password,String passwordVerification,Connection con) {
        String oldpassword = null;
        Boolean bool = false;

        try {
            // Envoi d’un requête générique
            String sql =  "select * from membre WHERE id=" + id;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                oldpassword = rs.getString("password");
            }

        }  catch (Exception e) {
            System.exit(1);
        }

        if(oldpassword.compareTo(old)==0 && password.compareTo(passwordVerification)==0){

            try{
                // create our java preparedstatement using a sql update query
                PreparedStatement ps = con.prepareStatement("UPDATE membre SET password ='"+passwordVerification+"' WHERE id ="+id);

                // set the preparedstatement parameters
                //ps.setString(1,passwordVerification);
                //ps.setInt(2,id);

                // call executeUpdate to execute our sql update statement
                int exec = ps.executeUpdate();
                ps.close();

                bool = (exec == 1);
            }
            catch (SQLException se){
                System.exit(1);
            }

        }else {
            return false;
        }

        return bool;
    }


    /**
     * ExistUser permet de savoir si un utilisateur existe
     *
     * @param pseudo son pseudo
     * @return un boolean permettant de savoir si oui ou non l'utilisateur existe
     */
    @Override
    public Boolean existPseudo(String pseudo) {
        Connection con = Connect.connection();
        return _existPseudo(pseudo,con);
    }

    protected Boolean _existPseudo(String pseudo,Connection con) {

        Boolean bool = false;
        int nb = 0;


    try{
        Statement stmt = con.createStatement();

        String sql = "SELECT COUNT(*) AS nb FROM membre WHERE pseudo='"+pseudo+"'";
        ResultSet rs = stmt.executeQuery(sql);
        //STEP 5: Extract data from result set
        while(rs.next()){
            //Retrieve by column name
            nb  = rs.getInt("nb");
        }
        rs.close();

        bool = (nb > 0); // Si il y a plus de 0 membres

    }catch(SQLException se){
        //Handle errors for JDBC
        bool = false;
    }
    return bool;

    }

    /**
     * deleteUser permet de supprimer des utilisateurs
     * @param pseudo le pseudo de l'utilisateur
     * @return un boolean permettant de savoir si oui ou non l'utilisateur a bien été supprimé
     */
    @Override
    public Boolean deleteUser(String pseudo) {
        Connection con = Connect.connection();
        return _deleteUser(pseudo,con);
    }

    protected Boolean _deleteUser(String pseudo,Connection con) {

        Boolean bool = false;

        try{
            // create our java preparedstatement using a sql update query
            PreparedStatement ps = con.prepareStatement("DELETE FROM membre WHERE pseudo = ?");

            // set the preparedstatement parameters
            ps.setString(1,pseudo);

            // call executeUpdate to execute our sql update statement
            int exec = ps.executeUpdate();
            ps.close();

            bool = (exec == 1);
        }
        catch (SQLException se){
            se.printStackTrace();
            System.exit(1);
        }
        return bool;

    }

    /**
     * Permet d'avoir la liste des exercices avec leurs id
     * @return un dictionnaire associant l'id de l'exercice à son nom
     */
    @Override
    public Hashtable<Integer, String> getExerciceList() {
        Connection con = Connect.connection();
        return _getExerciceList(con);
    }

    protected Hashtable<Integer, String> _getExerciceList(Connection con) {
        Hashtable hashtable = new Hashtable<Integer, String>();

        try {
            // Envoi d’un requête générique
            String sql =  "select * from exercice";
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String exercice = rs.getString("exercice");

                hashtable.put(id,exercice);
            }
        }  catch (Exception e) {
            System.exit(1);
        }
        return hashtable;
    }

    /**
     * Permet d'avoir l'avancement de l'utilisateur
     * @param userID L'id de l'utilisateur
     * @return retourne la liste d'avancement avec notamment les id des exercice, leurs niveau et leurs pourcentage
     */
    @Override
    public HashSet<Avancement> getUserAvancement(int userID) {
        Connection con = Connect.connection();
        return _getUserAvancement(userID,con);
    }

    protected HashSet<Avancement> _getUserAvancement(int userID,Connection con) {
        HashSet hashSet = new HashSet<Avancement>();

        try {
            // Envoi d’un requête générique
            String sql =  "select * from avancement WHERE pseudoId="+userID;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                Integer exerciceId = rs.getInt("exerciceId");
                Integer niveau = rs.getInt("niveau");
                Integer pourcentage = rs.getInt("pourcentage");

                Avancement avancement = new Avancement(exerciceId,niveau,pourcentage);

                hashSet.add(avancement);
            }
        }  catch (Exception e) {
            System.exit(1);
        }
        return hashSet;
    }

    /**
     * Permet de savoir l'avancement pour un exercice donné
     * @param UserID l'id de l'utilisateur
     * @param ExerciceID l'id de l'exercice
     * @return la liste d'avancement pour un exercice donné avec chaque niveau
     */
    @Override
    public HashSet<Avancement> getUserAvancementOf(int UserID, int ExerciceID) {
        Connection con = Connect.connection();
        return _getUserAvancementOf(UserID, ExerciceID,con);
    }

    protected HashSet<Avancement> _getUserAvancementOf(int UserID, int ExerciceID,Connection con) {
        HashSet hashSet = new HashSet<Avancement>();

        try {
            // Envoi d’un requête générique
            String sql =  "select * from avancement WHERE pseudoId="+UserID+" AND exerciceId="+ExerciceID;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                Integer exerciceId = rs.getInt("exerciceId");
                Integer niveau = rs.getInt("niveau");
                Integer pourcentage = rs.getInt("pourcentage");

                Avancement avancement = new Avancement(exerciceId,niveau,pourcentage);

                hashSet.add(avancement);
            }
        }  catch (Exception e) {
            System.exit(1);
        }
        return hashSet;
    }

    /**
     * Permet de savoir l'avancement pour un exercice et un niveau donné
     * @param UserID l'id de l'utilisateur
     * @param ExerciceID l'id de l'exercice
     * @param niveau le niveau
     * @return le pourcentage d'avancement
     */
    @Override
    public int getUserAvancementOfAt(int UserID, int ExerciceID, int niveau) {
        Connection con = Connect.connection();
        return _getUserAvancementOfAt(UserID, ExerciceID, niveau,con);
    }

    protected int _getUserAvancementOfAt(int UserID, int ExerciceID, int niveau,Connection con) {
        int pourcentage = 0;

        try {
            // Envoi d’un requête générique
            String sql =  "select * from avancement WHERE pseudoId="+UserID+" AND exerciceId="+ExerciceID+" AND niveau="+niveau;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                pourcentage = rs.getInt("pourcentage");
            }
        }  catch (Exception e) {
            System.exit(1);
        }
        return pourcentage;
    }

    /**
     * Permet de définir l'avancement d'un joueur
     * @param UserID l'id de l'utilisateur
     * @param ExerciceID l'id de l'exercice
     * @param pourcentage le pourcentage voulu
     * @return un boolean permettant de savoir si oui ou non l'avancement a été définie
     */
    @Override
    public Boolean setUserAvancement(int UserID,int ExerciceID,int niveau,int pourcentage) {
        Connection con = Connect.connection();
        return _setUserAvancement(UserID, ExerciceID, niveau, pourcentage,con);
    }
    protected Boolean _setUserAvancement(int UserID,int ExerciceID,int niveau,int pourcentage,Connection con) {
        int nb = 0;

        try {
            // Envoi d’un requête générique
            String sql =  "select COUNT(*) AS nb from avancement WHERE pseudoId="+UserID+" AND exerciceId="+ExerciceID+" AND niveau="+niveau;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                nb = rs.getInt("nb");
            }
        }  catch (Exception e) {
            System.exit(1);
        }

        if(nb != 0){
            return setMaxUserAvancement(UserID, ExerciceID, niveau, pourcentage);
        }else{
            Boolean bool = false;

            try{
                // create our java preparedstatement using a sql update query
                PreparedStatement ps = con.prepareStatement("INSERT INTO avancement(exerciceId,niveau,pourcentage,pseudoId) VALUES(?,?,?,?)");

                // set the preparedstatement parameters
                ps.setInt(1,ExerciceID);
                ps.setInt(2,niveau);
                ps.setInt(3,pourcentage);
                ps.setInt(4,UserID);

                // call executeUpdate to execute our sql update statement
                int exec = ps.executeUpdate();
                ps.close();

                bool = (exec == 1);
            }
            catch (SQLException se){
                se.printStackTrace();
                System.exit(1);
            }
            return bool;

        }
    }

    /**
     * Permet de définir l'avancement d'un joueur même si il l'a déjà, en mettant en avant le plus gros avancement present
     * @param UserID l'id de l'utilisateur
     * @param ExerciceID l'id de l'exercice
     * @param pourcentage le pourcentage voulu
     * @return un boolean permettant de savoir si oui ou non l'avancement a été définie
     */
    @Override
    public Boolean setMaxUserAvancement(int UserID,int ExerciceID,int niveau,int pourcentage) {
        Connection con = Connect.connection();
        return _setMaxUserAvancement(UserID, ExerciceID, niveau, pourcentage,con);
    }
    protected Boolean _setMaxUserAvancement(int UserID,int ExerciceID,int niveau,int pourcentage,Connection con) {

        int nb = 0;

        try {
            // Envoi d’un requête générique
            String sql =  "select pourcentage AS nb from avancement WHERE pseudoId="+UserID+" AND exerciceId="+ExerciceID+" AND niveau="+niveau;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;

            while (rs.next()) {
                nb = rs.getInt("nb");
            }
        }  catch (Exception e) {
            System.exit(1);
        }

        if(nb<pourcentage) {


            Boolean bool = false;
            try {
                // create our java preparedstatement using a sql update query
                PreparedStatement ps = con.prepareStatement("UPDATE avancement SET pourcentage=" + pourcentage + " WHERE pseudoId=" + UserID + " AND exerciceId=" + ExerciceID + " AND niveau=" + niveau);

                // call executeUpdate to execute our sql update statement
                int exec = ps.executeUpdate();
                ps.close();

                bool = (exec == 1);
            } catch (SQLException se) {
                System.exit(1);
            }

            return bool;
        }else{
            return true;
        }
    }


    /**
     * La fonction existEmail permet de savoir si un email est existant
     * @param email l'email à check
     * @return un boolean retournant vrai ou faux en fonction de la bdd
     */
    @Override
    public Boolean existEmail(String email) {
        Connection con = Connect.connection();
        return _existEmail(email,con);
    }

    private Boolean _existEmail(String email,Connection con) {
        Boolean bool = false;
        int nb = 0;


        try{
            Statement stmt = con.createStatement();

            String sql = "SELECT COUNT(*) AS nb FROM membre WHERE email='"+email+"'";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                nb  = rs.getInt("nb");
            }
            rs.close();

            bool = (nb > 0); // Si il y a plus de 0 membres

        }catch(SQLException se){
            //Handle errors for JDBC
            bool = false;
        }
        return bool;

    }

//    public static void main(String[] args) {
//
//
//        Database database = new MySQLDatabase();
//
//
//        /*
//        Hashtable hashtable = new Hashtable<Integer, String>();
//        hashtable = database.getExerciceList();
//        System.out.println(hashtable.toString());
//        */
//
//        /*
//        HashSet hashSet = new HashSet<Avancement>();
//        hashSet = database.getUserAvancement(100);
//        Iterator it = hashSet.iterator();
//        while (it.hasNext()){
//            Avancement avancement = Avancement.class.cast(it.next());
//            System.out.println(avancement.getExerciceId());
//            System.out.println(avancement.getLevelId());
//            System.out.println(avancement.getPourcentage());
//            System.out.println("\n");
//        }
//        */
//
//        /*
//        HashSet hashSet = new HashSet<Avancement>();
//        hashSet = database.getUserAvancementOf(100,2);
//        Iterator it = hashSet.iterator();
//        while (it.hasNext()){
//            Avancement avancement = Avancement.class.cast(it.next());
//            System.out.println(avancement.getExerciceId());
//            System.out.println(avancement.getLevelId());
//           System.out.println(avancement.getPourcentage());
//           System.out.println("\n");
//       }
//       */
//
//        /*
//       int pourcentage;
//       pourcentage = database.getUserAvancementOfAt(100,2,2);
//       System.out.println(pourcentage);
//       int pourcentage2;
//       pourcentage2 = database.getUserAvancementOfAt(100,2,3);
//       System.out.println(pourcentage2);
//       *//*
//       Boolean bool;
//        bool = database.setUserAvancement(100,2,3,65);
//        System.out.println(bool);
//        */
//
//        /*
//        Boolean bool;
//        bool = database.existEmail("tt@tt.com");
//        System.out.println(bool);
//        */
//   }


}
