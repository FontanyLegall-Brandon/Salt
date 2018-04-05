package com.example.utilisateur.projetl3.gameTemplates.MoteurJeux.randomEqualities;

/**
 * Created by theo on 4/4/18.
 */

public class EndCard extends RandomEquality {
    private int score;
    public EndCard(int score) {
        super();
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (score < 0) {
            builder.append("FINI");
        } else {
            builder.append("Score : ");
            builder.append(this.score);
            builder.append(" points sur 10");
        }
        return builder.toString();
    }
}
