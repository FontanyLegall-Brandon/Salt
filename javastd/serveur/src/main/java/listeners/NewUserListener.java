package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
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

    }
}
