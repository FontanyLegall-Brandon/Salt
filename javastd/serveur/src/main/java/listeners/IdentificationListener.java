package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

import listeners.mappers.LoginInfos;
import serveur.Server;

public class IdentificationListener implements DataListener<LoginInfos> {
	
	private Server serveur;
	
	public IdentificationListener(Server server) {
		this.serveur = server;
	}
	
	public void onData(SocketIOClient socket, LoginInfos user, AckRequest ackRequest) {
		
	}

}
