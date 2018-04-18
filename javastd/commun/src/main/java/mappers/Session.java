package mappers;

/**
 * La classe Session permet, lors de la connexion, de retourner à l'application les informations du compte pour
 * de futur interraction avec le serveur comme par exemple l'id ou le pseudo du compte.
 * Uniquement des Getters sont present dans cette classe pour éviter pour crack
 */
public class Session {
    private final int id;
    private final String pseudo;
    private final String prenom;
    private final String nom;
    private final String email;
    private final int age;
    
    /**
     * Le constructeur de la classe Session, il regroupe toutes les informations d'un compte :
     * <ul>
     *     <li>son identifiant(id) <strong>unique</strong></li>
     *     <li>son pseudo <strong>unique</strong></li>
     *     <li>son nom</li>
     *     <li>son prenom</li>
     *     <li>son email <strong>unique</strong></li>
     *     <li>son age</li>
     * </ul>
     *
     * @param id l'id du compte
     * @param pseudo le pseudo du compte
     * @param nom le nom de la personne ayant créé le compte
     * @param prenom le prenom de la personne ayant créé le compte
     * @param email l'email du compte
     * @param age l'age de la personne ayant créé le compte
     */
    public Session(int id, String pseudo, String prenom, String nom, String email, int age) {

        this.id = id;
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
