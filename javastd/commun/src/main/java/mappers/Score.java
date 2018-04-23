package mappers;

public class Score {
    private int idSession;
    private int note;
    private int exercice; // Exercice est un int (code - probablement plus propre avec une enum)

    public Score() {

    }

    public Score(int exercice, int note) {
        this.note= note;
        this.exercice = exercice;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getExercice() {
        return exercice;
    }

    public void setExercice(int exercice) {
        this.exercice = exercice;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public int getIdSession() {
        return this.getIdSession();
    }
}
