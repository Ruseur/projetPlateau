package Model.Jeu;

import Model.Plateau.Grille;

import java.util.Observable;

public class Jeu extends Observable {
    // "playing" "paused" "initial" "finished"
    private String status = "initial";
    private Grille grille;
    private int Score = 0;

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
        setChanged();
        notifyObservers("ScoreUpdate");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setChanged();
        notifyObservers("StatusUpdate");
    }
}
