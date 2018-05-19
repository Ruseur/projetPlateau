package Model.Jeu;

import Model.Plateau.Grille;
import Model.Plateau.Piece;

public class Tetris extends Jeu{

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
