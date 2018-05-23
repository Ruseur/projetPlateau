package Model.Jeu;

import Model.Joueur.Joueur;
import Model.Plateau.Grille;

public class Tetris extends Jeu{

    private Joueur joueur;
    private Grille nextPieceGrille;
    private int level;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setChanged();
        notifyObservers("LevelUpdate");
    }
}
