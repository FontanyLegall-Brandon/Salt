package database;


import serveur.Avancement;

import java.util.HashSet;
import java.util.Hashtable;

import mappers.Session;

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
  Boolean existPseudo(String pseudo);
  Boolean existEmail(String email);
  Boolean deleteUser(String pseudo);
  HashSet<Avancement> getUserAvancement(int userID);
  HashSet<Avancement> getUserAvancementOf(int UserID,int ExerciceID);
  int getUserAvancementOfAt(int UserID,int ExerciceID,int niveau);
  Boolean setUserAvancement(int UserID,int ExerciceID,int niveau,int pourcentage);
  Boolean setMaxUserAvancement(int UserID,int ExerciceID,int niveau,int pourcentage);

  HashSet<Avancement> getUserScore(int userID);
  HashSet<Avancement> getUserScoreOf(int UserID,int ExerciceID);
  int getUseScoreOfAt(int UserID,int ExerciceID);
  Boolean setUserScore(int UserID,int ExerciceID
          ,int score);
  Boolean setMaxUserScore(int UserID,int ExerciceID,int niveau,int score);




  //Fonctions liés aux données :
  Hashtable<Integer,String> getExerciceList();

  
}