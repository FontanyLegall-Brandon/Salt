package com.example.utilisateur.projetl3.network;
import android.util.Log;
import android.widget.Toast;

import com.example.utilisateur.projetl3.ActivityForIO;
import com.example.utilisateur.projetl3.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by theo on 3/28/18.
 */

public enum Singleton {
    CLIENT;
    private final int nbJeux = 5;
    private Socket mSocket;
    private boolean failedConnect;
    private ActivityForIO activity;
    private int[] progression = new int[nbJeux];//cinq jeux pour l'instant

    private Singleton() {
        connect();
        failedConnect = false;
    }

    public void connect() { // Gère la connexion au serveur
        boolean connected = false;
        String urlconnection = "http://192.168.43.212:10005"; //10.0.2.2 en local
        try {
            Log.d("connexion", urlconnection);
            mSocket = IO.socket(urlconnection);
            mSocket.connect();
            mSocket.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    if (activity != null) {
                        activity.displayToast("connecté", Toast.LENGTH_LONG);
                    }
                    Log.d("connexion", "connecté");
                }
            });

            mSocket.on("connect_error", new Emitter.Listener() {
                // dans le cas d'une erreur de connexion
                @Override
                public void call(Object... args) {
                    if (activity != null && !failedConnect) {
                        // Affichage d'un toast d'avertissement
                        activity.displayToast("erreur lors de la connexion, votre progrès ne pourra pas être sauvegardé",
                                Toast.LENGTH_LONG);
                    }
                    failedConnect = true;
                }
            });

            mSocket.on("connect_timeout", new Emitter.Listener() {
                // dans le cas d'un délai de connexion trop long
                @Override
                public void call(Object... args) {
                    if (activity != null) {
                        activity.displayToast("timeout, reconnexion...",
                                Toast.LENGTH_LONG);
                    }
                    connect();
                }
            });



        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void sendNewUser(RegisterRequest request) {
        if ((mSocket != null) && (mSocket.connected())) {
            JSONObject obj = new JSONObject();
            HashMap<String, String> map = request.getParams();
            try {
                for (String id : map.keySet()) {
                    obj.put(id, map.get(id));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mSocket.emit("newUser", obj);
        }
    }

    public void sendLogin(String login, String password) {
        // Tentative de login, envoi des identifiants au serveur
        if ((mSocket != null) && (mSocket.connected())) {
            // Si on est connecté au serveur

            JSONObject obj = new JSONObject(); // Création du JSON qui contient le message
            try {
                // On construit le message avec les identifiants : email + mdp
                obj.put("email", login);
                obj.put("password", password);

                // Puis on l'envoi au serveur
                mSocket.emit("login", obj);

                // On applique pour finir un listener pour gérer le retour
                mSocket.on("session", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {

                        // Réccupération du message envoyé par le serveur
                        JSONObject session = (JSONObject) args[0];

                        int id = -1;
                        String pseudo;
                        String prenom;
                        String nom;
                        String email;
                        int age;

                        try {   // On réccupère tous les champs de la session, exception si les champs ne sont pas bien réccupérés
                            /*
                            Objet session, dans lequel insérer tous ces champs
                             */
                            id = (int) session.get("id"); // l'id de session
                            pseudo = (String) session.get("pseudo");
                            prenom = (String) session.get("prenom");
                            nom = (String) session.get("nom");
                            email = (String) session.get("email"); // Utile ? Le mail est le login qui sert à réccupérer cette session
                            age = (int) session.get("age");
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //activity.displayToast("ID : " + id, Toast.LENGTH_LONG);


                    }

                });

                //TODO Listener pour gérer les logins incorrects



            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean is_connected() {
        return mSocket.connected();
    }

    public void setActivity(ActivityForIO activity) {
        this.activity = activity;
    }

    public void incScore(int codeJeu, int n) {
        if (n > progression[codeJeu]) {
            progression[codeJeu] = n;

            if (CLIENT.is_connected()) {
                //envoi du score
            }
        }
    }

    public int getAvancement() {
        int avancement = 0;
        for (int i : progression) {
            avancement += i;
        }
        return avancement;
    }

    public int getMaxProgression() {
        return 10*nbJeux;
    }
}
