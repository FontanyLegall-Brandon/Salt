package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import database.Database;
import listeners.mappers.Reply;
import listeners.mappers.UserInfo;
import serveur.Server;

/**
 * Listener pour le cas
 */
public class NewUserListener implements DataListener<UserInfo> {

    // Un unique champ : le serveur auquel est rataché le listener
    private Server server;

    /**
     *Retourne un NewUserListener, en charge de gérer la demande d'inscription
     * @param server
     */
    public NewUserListener(Server server) {
        // Le constructeur ne fait rien d'autre que d'instancier le champ serveur
        this.server = server;
    }

    /**
     * Action à réaliser à chaque évenement UserInfo
     * @param socket
     * Le socket du client dont on reçoit le message
     * @param user
     * Le message JSON mappé
     * @param ackRequest
     * La demande d'acknowledgemnt du client
     */
    public void onData(SocketIOClient socket, UserInfo user, AckRequest ackRequest) {

        Database database = server.getDatabase();

        if (database.existUser(user.getPseudo())) {//si le joueur existe déjà
            socket.sendEvent("userAlreadyExists", new Reply("userAlreadyExists"));
        }
        else { // Si le joueur n'existe pas encore
            /*
            Peut être faudrait-il vérifier l'unicité de l'adresse e-mail également, ainsi que d'autres
            attributs…
             */

            // On inscrit le joueur dans la BDD
            if(database.addUser(user.getPseudo(), user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword(), user.getAge())) {
                // Dans le cas ou l'inscrition a marché
                System.out.println("Sign up successful");
                socket.sendEvent("signedUp", new Reply("signedUp"));
            }
            else {  // Cas d'une iscription qui n'a pas marché, mais pas à cause d'un nom d'utilisateur utilisé
                System.out.println("Sign up failed");
                socket.sendEvent("signUpFailure", new Reply("signUpFailure"));
            }
        }

    }
}
