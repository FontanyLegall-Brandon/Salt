public class StartServer {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Syntaxe : StartServer hostname port");
            System.exit(-1);
        } else {
            try {
                int port = Integer.parseInt(args[2]);
                Server serveur = new Server(args[1], port);
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
