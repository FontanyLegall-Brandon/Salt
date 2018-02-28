package com.example.utilisateur.projetl3.network;

/**
 * Created by theo on 2/28/18.
 */

import io.socket.client.Socket;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Philippe on 21/02/2018.
 */

public class Client {
    Socket mSocket;

    public Client() {
    }


    public void disconnect() {
        if (mSocket != null) mSocket.disconnect();
    }

    public void connect() {

        try {
            Log.d("connexion", "http://77.136.82.173:1345");
            mSocket = IO.socket("http://77.136.82.173:1345");

            mSocket.on("couleur", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d("connexion", "message recu "+args[0]);
                }
            });
            Log.d("connexion", ""+mSocket);
            mSocket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /*protected void receiveColor(Object[] args) {
        JSONObject o = (JSONObject) args[0];
        try {

        } catch (JSONException e) {
        }
    }*/

    /*public void sendColor(int r) {
        if ((mSocket != null) && (mSocket.connected())) {
            Couleur c = new Couleur(r,r,r);
            JSONObject obj = new JSONObject();
            try {
                obj.put("bleu", c.getBleu());
                obj.put("rouge", c.getRouge());
                obj.put("vert", c.getVert());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mSocket.emit("couleur", obj);
        }
    }*/
}