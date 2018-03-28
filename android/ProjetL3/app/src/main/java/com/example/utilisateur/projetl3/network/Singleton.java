package com.example.utilisateur.projetl3.network;
import android.util.Log;

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
    private Singleton() {
        connect();
    }

    public void connect() {
        boolean connected = false;
        try {
            Log.d("connexion", "http://192.168.124.1:10000");
            mSocket = IO.socket("http://192.168.124.1:10000");
            mSocket.connect();
            mSocket.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d("connexion", "connecté");
                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
