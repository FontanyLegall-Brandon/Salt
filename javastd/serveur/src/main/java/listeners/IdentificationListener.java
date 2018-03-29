package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

import database.Database;
import listeners.mappers.LoginInfos;
import listeners.mappers.Session;
import serveur.Server;

public class IdentificationListener implements DataListener<LoginInfos> {
	
	private Server serveur;
	
	public IdentificationListener(Server server) {
		this.serveur = server;
	}

	@Override
	public void onData(SocketIOClient socket, LoginInfos user, AckRequest ackRequest) {

		Database database = this.serveur.getDatabase();

		String email = user.getEmail();
		String password = user.getPassword();

		Session session = database.connection(email, password);

		if (session.getId() == 0) {     // Mauvais login, on récupère une session vide donc avec un id 0
		    socket.sendEvent("badCredentials", null);
        }

        else {  // Login fonctionnel, on renvoit la session
		    socket.sendEvent("session", session);
        }

	}

}
