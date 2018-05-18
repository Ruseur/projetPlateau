package Model.Jeu;

import Model.Plateau.Grille;

import java.util.Observable;

public class Jeu extends Observable {
    protected Grille grille;

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }
}
