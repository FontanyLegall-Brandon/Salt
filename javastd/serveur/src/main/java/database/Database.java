package database;

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

  public void addUser (String nom);
  
}