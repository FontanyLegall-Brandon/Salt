package com.example.utilisateur.projetl3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.utilisateur.projetl3.network.Singleton;
import com.example.utilisateur.projetl3.network.SingletonInteraction;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by theo on 3/28/18.
 */

public abstract class ActivityForIO extends Activity implements SingletonInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Singleton.CLIENT.setActivity(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Affiche un toast dans l'application
     * @param message Le message à afficher
     * @param duration La durée de l'affichage
     */
    public void displayToast( final String message, final int duration) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, duration).show();
            }
        });
    }

    /**
     * Méthode appelée lorsque le client a bien reçu une session valide lors de la connexion.
     * Permet de lancer les opérations de réinitialisation de l'interface en appellant les méthodes dans le bon thread.
     */
    public void successfulLogin() {

        final ActivityForIO activity = this;    // Sauvegarde de l'activité courante, this fera référence au runable quand on en aura besoin

        runOnUiThread(new Runnable() {  // On effectue l'opération sur le UI thread (?) en instanciant une classe abstraite Runnable
            @Override
            public void run() {         // dont la méthode run() se contente d'appeler la méthode resetLoginScreen()

                try {
                    activity.stopWaitingForLoginReply();
                    activity.startActivity(new Intent(activity, MenuPrincipal.class));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Ré-initialise l'état de l'écran de login lorsque le serveur répond négativement à la tentative
     */
    public void failedLogin() {

        final ActivityForIO activity = this;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    activity.stopWaitingForLoginReply();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Signal à l'utilisateur que son compte ne sera pas créé puisque son pseudo est déjà pris
     */
    public void pseudoExists() {

        final ActivityForIO activity = this;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    activity.setPseudoRed();
                    activity.displayToast("Le nom d'utilisateur est déjà utilisé, veuillez en choisir un autre", Toast.LENGTH_LONG);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void emailExists() {

        final ActivityForIO activity = this;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    activity.setEmailRed();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                activity.displayToast("L'email est déjà utilisé, veuillez en choisir un autre", Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * Sort le Menu principal de son état d'attente quand il essaye de se loguer. Remet à zéro les boutons
     * @throws NoSuchMethodException lorsque cette méthode est appelée sur une activité qui n'est pas un MenuPricipal
     */
    public void stopWaitingForLoginReply() throws NoSuchMethodException {
        throw new NoSuchMethodException("resetLoginScreen called from another activity than Menu");
    }

    /**
     * Colore le texte éditable pseudo en rouge dans l'écran d'inscription.
     * @throws NoSuchMethodException lorsque cette méthode est appelée sur une activité qui n'est pas RegisterActivity
     */
    public void setPseudoRed() throws NoSuchMethodException {
        throw new NoSuchMethodException("setPseudoRed called from another activity than RegisterActivity");
    }

    public void setEmailRed() throws NoSuchMethodException {
        throw new NoSuchMethodException("setEmailRed called from another activity than RegisterActivity");
    }


}
