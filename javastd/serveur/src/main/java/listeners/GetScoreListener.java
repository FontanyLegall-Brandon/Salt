package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import listeners.mappers.Score;
import serveur.Server;

public class GetScoreListener implements DataListener<Score> {

    Server server;

    public GetScoreListener(Server server) {
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, Score score, AckRequest ackRequest) {
        System.out.println("Not yet implemented");
    }
}
