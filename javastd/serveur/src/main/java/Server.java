import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

public class Server{
    private Configuration config;
    private ConnectListener listener;
    public Server(String hostname, int port) {
        config = new Configuration();
        config.setHostname(hostname);
        config.setPort(port);
        listener = new ConnectListener() {
            public void onConnect(SocketIOClient client) {
                
            }
        };
    }

    public void run() {
        final SocketIOServer server = new SocketIOServer(config);
        server.start();
        server.addConnectListener();
    }
}
