package db;

import db.Db;

public class MockDb implements Db {
  
  public void addUser(String nom) {
    System.out.println(nom);
  }
  
}