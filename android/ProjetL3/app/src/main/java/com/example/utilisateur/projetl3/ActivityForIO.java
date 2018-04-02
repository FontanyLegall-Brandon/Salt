package com.example.utilisateur.projetl3;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.utilisateur.projetl3.network.SingletonInteraction;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by theo on 3/28/18.
 */

public abstract class ActivityForIO extends Activity implements SingletonInteraction {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void displayToast( final String msg, final int length) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, length).show();
            }
        });
    }

}
