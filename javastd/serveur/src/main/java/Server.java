import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class Server{
    private Configuration config;
    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;

    public Server(String hostname, int port) {
        config = new Configuration();
        config.setHostname(hostname);
        config.setPort(port);
        connectListener = new ConnectListener() {
            public void onConnect(SocketIOClient client) {
                StringBuilder builder = new StringBuilder();
                builder.append(client);
                builder.append(" s'est connecté");
                System.out.println(builder.toString());
            }
        };

        disconnectListener = new DisconnectListener() {
            public void onDisconnect(SocketIOClient client) {
                StringBuilder builder = new StringBuilder();
                builder.append("Déconnexion du client ");
                builder.append(client);
                System.out.println(builder.toString());
            }
        };
    }

    public void run() {
        final SocketIOServer server = new SocketIOServer(config);
        server.start();
        server.addConnectListener(connectListener);
        server.addDisconnectListener(disconnectListener);
    }
}
