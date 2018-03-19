package database;


import serveur.Session;

/**
 * la database sous forme d'interface va nous permettre d'interragir entre le serveur et la database.
 * Notamment avec les fonctions de :
 * <ul>
 *     <li>Connection à la database</li>
 *     <li>Connexion à un compte</li>
 *     <li>Création d'un compte</li>
 *     <li>...</li>
 * </ul>j
 */
public interface Database {
  //TODO Identifier et coder les methodes d'une database

  public Boolean addUser(String pseudo,String nom,String prenom, String email,String password,int age);
  public Session connection(String email, String password);
  public Boolean editPassword(int id,String old,String password,String passwordVerification);
  public Boolean existUser(String pseudo);
  public Boolean deleteUser(String pseudo);
  
}