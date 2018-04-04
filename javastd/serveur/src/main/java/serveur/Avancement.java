package serveur;

/**
 * La classe avancement permet de retourner l'avancement d'un joueur via l'appel bdd
 */
public class Avancement {
    private final int exerciceId;
    private final int levelId;
    private final int pourcentage;

    /**
     * Le constructeur de la classe avancement regroupe les informations d'avancement du joueur, avec :
     * <ul>
     *     <li>un exercice</li>
     *     <li>un niveau</li>
     *     <li>le pourcentage d'avancement du niveau</li>
     * </ul>
     *
     * @param exerciceId
     * @param levelId
     * @param pourcentage
     */
    public Avancement(int exerciceId, int levelId, int pourcentage) {
        this.exerciceId = exerciceId;
        this.levelId = levelId;
        this.pourcentage = pourcentage;
    }

    public int getExerciceId() {
        return exerciceId;
    }

    public int getLevelId() {
        return levelId;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avancement that = (Avancement) o;

        if (exerciceId != that.exerciceId) return false;
        if (levelId != that.levelId) return false;
        return pourcentage == that.pourcentage;
    }

    @Override
    public int hashCode() {
        int result = exerciceId;
        result = 31 * result + levelId;
        result = 31 * result + pourcentage;
        return result;
    }
}
