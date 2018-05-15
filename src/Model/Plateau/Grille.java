package Model.Plateau;

import java.util.Observable;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Grille extends Observable {

    private int hauteur, largeur;
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

    public void entered(int x, int y){
        grilleCase[x][y].setColor(Color.BLACK);

        setChanged();
        notifyObservers(grilleCase[x][y]);
    }

    public void exited(int x, int y){
        grilleCase[x][y].setColor(Color.RED);

        setChanged();
        notifyObservers(grilleCase[x][y]);
    }


    /**
     * @return the hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @param hauteur the hauteur to set
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * @return the largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
