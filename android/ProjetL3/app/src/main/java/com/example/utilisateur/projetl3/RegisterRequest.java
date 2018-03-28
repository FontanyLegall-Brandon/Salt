package com.example.utilisateur.projetl3;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by inesr on 28/02/2018.
 */

public class RegisterRequest {
    private HashMap<String, String> params;

    //le constructeur
    public RegisterRequest(String prenom, String nom, int age, String email, String pseudo, String mdp) {
        //on veut envoyer ce donnees a notre BDD
        params = new HashMap<>();
        params.put("prenom", prenom);
        params.put("nom", nom);
        params.put("age", age + ""); //conversion int to string
        params.put("email", email);
        params.put("pseudo", pseudo);
        params.put("password", mdp);
    }

    public HashMap<String, String> getParams() {
        return params;
    }
}
