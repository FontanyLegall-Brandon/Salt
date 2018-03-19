import serveur.Server;

/**
 * Point d'entrée pour le lancement du serveur
 */
public class StartServer {

    /*
        Fonction main
     */
    public static void main(String[] args) {

        if (args.length != 2) { // Si on a pas le bon nombre d'argument

            // On affiche une erreur
            System.err.println("Syntaxe : StartServer hostname port");

            // et on les affiche
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + " :" + args[i]);
            }

            System.exit(-1);    // Fin de l'execution
        }

        else {  // Cas où on a le bon nombre d'arguments

            int port;

            try {
                // On essaye de parser en int le numéro de port
                port = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                // Si ce n'est pas possible, message d'erreur et on fixe un numéro arbitraire
                System.err.println("Mauvais numéro de port, utilisation du port 10101");
                port = 10101;
            }

            // Instanciation et lancement du serveur
            Server serveur = new Server(args[0], port);
            serveur.run();
        }
    }
}
