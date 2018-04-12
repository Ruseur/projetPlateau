package Model.Plateau;

import java.util.ArrayList;

public class Grille {

    private Case[][] grilleCase;
    ArrayList<Piece> listePieces;

    public Grille(int largeurPlateau, int hauteurPlateau) {
        this.grilleCase = new Case[hauteurPlateau][largeurPlateau];

        for(int i  = 0; i < hauteurPlateau; i++){
            for(int j  = 0; j < largeurPlateau; j++){
                this.grilleCase[i][j] = new Case(i,j);
            }
        }

        this.listePieces = new ArrayList<>();
    }

    public Case[][] getGrilleCase() {
        return grilleCase;
    }

    public void addPiece(Piece p){
        this.listePieces.add(p);
    }
}
