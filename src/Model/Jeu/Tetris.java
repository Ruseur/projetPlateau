package Model.Jeu;

import Model.Plateau.Grille;
import Model.Plateau.Piece;

public class Tetris extends Jeu{

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
}
