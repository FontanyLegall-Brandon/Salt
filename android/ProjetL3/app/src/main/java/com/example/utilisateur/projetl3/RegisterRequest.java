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

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "...";
    private Map<String, String> params;

    //le constructeur
    public RegisterRequest(String prenom, String nom, int age, String email, String pseudo, String mdp, Response.Listener<String> listener) {
        //on veut envoyer ce donnees a notre BDD
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("prenom", prenom);
        params.put("nom", nom);
        params.put("age", age + ""); //convertion int to string
        params.put("email", email);
        params.put("pseudo", pseudo);
        params.put("mdp", mdp);
    }

    @Override // afin que volley accede à ces données,
    // when the request is executed, volley will call getParams and it will return params filled with all the data up here :))
    public Map<String, String> getParams() {
        return params;
    }
}
