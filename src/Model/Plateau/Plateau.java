package Model.Plateau;

import Model.Jeu.Jeu;

import java.util.Observable;

public class Plateau extends Observable {
    private Jeu jeu;

    public Plateau() {

    }

    public Plateau(Jeu jeu) {
        this.jeu = jeu;
    }


    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
        setChanged();
        notifyObservers(jeu);
    }
}
