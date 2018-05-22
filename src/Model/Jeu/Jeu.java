package Model.Jeu;

import Model.Plateau.Grille;

import java.util.Observable;

public class Jeu extends Observable {
    // "playing" "paused" "initial" "finished"
    private String status = "initial";
    private Grille grille;
    private int level;

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setChanged();
        notifyObservers("StatusUpdate");
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setChanged();
        notifyObservers("LevelUpdate");
    }
}
