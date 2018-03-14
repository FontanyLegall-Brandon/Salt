import serveur.Server;

public class StartServer {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Syntaxe : StartServer hostname port");
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + " :" + args[i]);
            }
            System.exit(-1);
        } else {
            try {
                int port = Integer.parseInt(args[1]);
                Server serveur = new Server(args[0], port);
                serveur.run();
            }
            catch (NumberFormatException e) {
                System.err.println("Mauvais numéro de port, utilisation du port 10101");
                Server serveur = new Server(args[1], 10101);
                serveur.run();
            }
        }
    }
}
