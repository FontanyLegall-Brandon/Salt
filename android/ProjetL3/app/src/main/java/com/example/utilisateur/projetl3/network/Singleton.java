package com.example.utilisateur.projetl3.network;
import android.util.Log;
import android.widget.Toast;

import com.example.utilisateur.projetl3.ActivityForIO;
import com.example.utilisateur.projetl3.RegisterRequest;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by theo on 3/28/18.
 */

public enum Singleton {
    CLIENT;
    private Socket mSocket;
    private boolean failedConnect;
    private Singleton() {
        connect();
        failedConnect=false;
    }
    private ActivityForIO activity;
    public void connect() {
        boolean connected = false;
        try {
            Log.d("connexion", "http://192.168.43.244:1245");
            mSocket = IO.socket("http://192.168.43.244:1245");
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
            mSocket.on("connect_error", new Emitter.Listener() {//dans le cas d'une erreur de connexion
                @Override
                public void call(Object... args) {
                    if (activity != null && !failedConnect) {
                        activity.displayToast("erreur lors de la connexion, votre progrès ne pourra pas être sauvegardé",
                                Toast.LENGTH_LONG);
                    }
                    failedConnect = true;
                }
            });
            mSocket.on("connect_timeout", new Emitter.Listener() {//dans le cas d'un délai de connexion trop long
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

    public boolean is_connected() {
        return mSocket.connected();
    }


    public void setActivity(ActivityForIO activity) {
        this.activity = activity;
    }
}
