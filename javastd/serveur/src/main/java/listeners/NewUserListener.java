package listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import listeners.mappers.UserInfo;

public class NewUserListener implements DataListener<UserInfo> {
    public NewUserListener() {

    }

    public void onData(SocketIOClient sock, UserInfo user, AckRequest request) {

    }
}
