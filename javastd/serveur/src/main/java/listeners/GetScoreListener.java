package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import database.Database;
import mappers.Score;
import org.json.JSONObject;
import serveur.Server;

public class GetScoreListener implements DataListener<Score> {

    Server server;

    public GetScoreListener(Server server) {
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, Score score, AckRequest ackRequest) {
        //récupération du score et création d'un nouvel objet
        int note = server.getDatabase().getUserAvancementOfAt(score.getIdSession(), score.getExercice(), 0);
        Score answer = new Score(score.getExercice(), note, score.getIdSession());

        System.out.println("Envoi du score au client...");
        socketIOClient.sendEvent("score", answer);
    }
}
