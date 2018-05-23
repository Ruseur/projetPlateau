package Model.Jeu;

import Model.Plateau.Grille;

import java.util.Observable;

public class Jeu extends Observable {
    // "playing" "paused" "initial" "finished"
    private String status = "initial";
    private Grille grille;

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
}
