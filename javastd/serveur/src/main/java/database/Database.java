package database;


import serveur.Session;

import java.util.Hashtable;

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

  //Fonctions liés au client :
  Boolean addUser(String pseudo,String nom,String prenom, String email,String password,int age);
  Session connection(String email, String password);
  Boolean editPassword(int id,String old,String password,String passwordVerification);
  Boolean existUser(String pseudo);
  Boolean deleteUser(String pseudo);
  //Hashtable<Integer,Integer> getUserAvancement(int userID); //Hashtable<exerciceID,pourcentage>
  //int getUserAvancementOf(int UserID,int ExerciceID);
  //Boolean setUserAvancement(int UserID,int ExerciceID,int pourcentage);
  //Boolean setMaxUserAvancement(int UserID,int ExerciceID,int pourcentage);

  //Fonctions liés aux données :
  //Hashtable<Integer,String> getExerciceList();
  //Hashtable<Integer,String> getLevelList();
  //int getLevelOfExercice(int exerciceID);
  //Hashtable<Integer,String> getExerciceOfLevel(int levelID);

  
}