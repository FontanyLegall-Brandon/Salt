package com.example.utilisateur.projetl3.Aides;

/**
 * Created by Utilisateur on 31/03/2018.
 */

public class Aide {
    private int color;
    private String intitule;
    private String description;

    public Aide(int color, String intitule, String description) {
        this.color = color;
        this.intitule = intitule;
        this.description = description;
    }

    public int getColor(){
        return color;
    }

    public String getIntitule(){
        return intitule;
    }

    public String getDescription(){
        return description;
    }
}
