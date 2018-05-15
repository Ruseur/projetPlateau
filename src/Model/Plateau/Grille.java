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

    public Case getCase(int y, int x){
        if(y >= 0 && y < grilleCase.length && x >= 0 && x < grilleCase[0].length){
            return grilleCase[y][x];
        }else{
            return null;
        }
    }

    public void addPiece(Piece p){
        this.listePieces.add(p);
    }
}
