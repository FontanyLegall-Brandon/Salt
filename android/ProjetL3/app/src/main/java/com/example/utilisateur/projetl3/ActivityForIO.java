package com.example.utilisateur.projetl3;

import android.app.Activity;
import android.widget.Toast;

import com.example.utilisateur.projetl3.network.SingletonInteraction;

/**
 * Created by theo on 3/28/18.
 */

public abstract class ActivityForIO extends Activity implements SingletonInteraction {

    public void displayToast( final String msg, final int length) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, length).show();
            }
        });
    }

}
