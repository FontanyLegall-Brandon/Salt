package mappers;

public class Score {

    private int valeur;
    private int exercice; // Exercice est un int ??? voir par rapport à l'intégration de la db

    public Score() {

    }

    public Score(int valeur, int exercice) {
        this.valeur = valeur;
        this.exercice = exercice;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getExercice() {
        return exercice;
    }

    public void setExercice(int exercice) {
        this.exercice = exercice;
    }

}
