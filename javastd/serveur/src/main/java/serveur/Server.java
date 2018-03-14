package serveur;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import listeners.NewUserListener;
import listeners.mappers.UserInfo;


import java.util.concurrent.TimeUnit;

public class Server {
    private Configuration config;
    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;
    private boolean running;
    private final SocketIOServer server;

    public Server(String hostname, int port) {
        this.running = false;
        config = new Configuration();
        config.setHostname(hostname);
        config.setPort(port);
        this.server = new SocketIOServer(config);

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
        server.addConnectListener(connectListener);
        server.addDisconnectListener(disconnectListener);
        server.addEventListener("newUser", UserInfo.class, new NewUserListener());
    }

    public void run() {
        server.start();
        running = true;

        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        server.stop();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
