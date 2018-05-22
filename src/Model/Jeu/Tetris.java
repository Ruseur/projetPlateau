package Model.Jeu;

import Model.Joueur.Joueur;
import Model.Plateau.Grille;

public class Tetris extends Jeu{

    private Joueur joueur;
    private Grille nextPieceGrille;

    public Tetris(){}

    public Tetris(Grille grille) {
        this.setGrille(grille);
    }

    public Grille getNextPieceGrille() {
        return nextPieceGrille;
    }

    public void setNextPieceGrille(Grille nextPieceGrille) {
        this.nextPieceGrille = nextPieceGrille;
        setChanged();
        notifyObservers(nextPieceGrille);
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        setChanged();
        notifyObservers("PlayerUpdate");
    }
}
