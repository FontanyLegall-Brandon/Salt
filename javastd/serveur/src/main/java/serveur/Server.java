package serveur;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import database.Database;
import listeners.NewUserListener;
import listeners.mappers.UserInfo;
import java.util.concurrent.TimeUnit;


/**
 * Classe Server modélisant le serveur de notre application
 *
 */
public class Server {

    /*
    Déclaration des attributs
     */

    private final SocketIOServer socketServer;
    private Database database;

    private boolean running;

    // Les listeners
    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;
    private DataListener<UserInfo> localNewUserListener;

    /**
     * Retourne le serveur du jeu
     * @param socketServer
     * Le web-socket serveur sur lequel sont ajoutés les listeners et qui est le point d'écoute du serveur
     * @param database
     * La base de donnée avec laquelle le serveur interragira pour stocker les informations utilisateur
     */
    public Server(SocketIOServer socketServer, Database database) {

        this.running = false;
        this.socketServer = socketServer;
        this.database = database;
        

        /* 
         * ******************************************************************
         * *	Définition des listeners de connexion et de déconnexion		*
         * ******************************************************************
         */
        connectListener = new ConnectListener() {
            /* La seule chose qu'on défini est la méthode
                onConnect spécifiée par l'interface
             */
            public void onConnect(SocketIOClient client) {
                // Pour l'instant ne fait qu'afficher le paramètre
                StringBuilder builder = new StringBuilder();
                builder.append(client);
                builder.append(" s'est connecté");
                System.out.println(builder.toString());
            }

        };

        // Même chose pour le disconnectListener
        disconnectListener = new DisconnectListener() {

            public void onDisconnect(SocketIOClient client) {
                // Ici aussi on ne fait qu'afficher un message
                StringBuilder builder = new StringBuilder();
                builder.append("Déconnexion du client ");
                builder.append(client);
                System.out.println(builder.toString());
            }

        };


        /*
         * **************************************************
         * *	Ajout des listeners au socket serveur		*
         * **************************************************
        */
        this.socketServer.addConnectListener(connectListener);
        this.socketServer.addDisconnectListener(disconnectListener);
        this.socketServer.addEventListener("newUser", UserInfo.class, new NewUserListener(this));
    }

    public Database getDatabase() {
        return database;
    }

    /**
     * Lance le serveur
     */
    public void run() {

        this.socketServer.start();    // Le socket ecoute

        this.running = true;    // Mise à jour de l'état


        try { // Le thread du serveur est endormi,
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Arrête le serveur
     */
    public void stop() {

        socketServer.stop();	// Le socket arrête d'écouter
        running = false;

    }

    /**
     * @return L'état de l'activité du serveur
     */
    public boolean isRunning() {
        return running;
    }


}
