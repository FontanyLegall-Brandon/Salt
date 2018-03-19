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

    private final SocketIOServer server;

    private Configuration config;

    private boolean running;

    private Database database;

    // Les listenenrs
    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;

    private NewUserListener newUserListener;
    private DataListener<UserInfo> localNewUserListener;

    /**
     * Constructeur du Server
     *
     * @param hostname
     * L'addresse à laquelle le serveur sera joignable
     *
     * @param port
     * Le port sur lequel le serveur sera joignable
     */
    public Server(String hostname, int port) {

        this.running = false;
        config = new Configuration();   // Instanciation de la configuration
        config.setHostname(hostname);   // Puis réglage de ses attributs
        config.setPort(port);

        /* Notre Server est en réalité un objet qui contient un attribut qui est un serveur
        (Un SocketIOServer) qu'on instancie ici, à l'aide la configuration réglée plus tôt
         */
        this.server = new SocketIOServer(config);

        /* On défini le connectListner, en implémentant de
            manière anonyme l'interface ConnectListener
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

        // Ici le listener est implémenté dans une classe spécifique
        newUserListener = new NewUserListener(this);

        // Peut être une solution ?? À voir
        localNewUserListener = new DataListener<UserInfo>() {
            // Grâce au type entre < > on peut faire autant de data listener qu'on veut comme ça…

            public void onData(SocketIOClient client, UserInfo info, AckRequest requete) {
                //TODO
            }

        };

        /*
            Ajout des listeners au server
        */
        this.server.addConnectListener(connectListener);
        this.server.addDisconnectListener(disconnectListener);


        // Ajout du NewUserListener défini ailleur
        this.server.addEventListener("newUser", UserInfo.class, newUserListener);
    }

    public Database getDatabase() {
        return database;
    }

    /**
     * Lance le serveur
     */
    public void run() {

        this.server.start();    // Lancement du serveur

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

        server.stop();
        running = false;

    }

    /**
     * @return L'état de l'activité du serveur
     */
    public boolean isRunning() {
        return running;
    }


}
