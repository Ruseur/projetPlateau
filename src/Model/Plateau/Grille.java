package Model.Plateau;

import java.util.Observable;
import javafx.scene.paint.Color;

public class Grille extends Observable {

    private int hauteur, largeur;
    private Case[][] grilleCase;
    
    public Grille(int hauteurPlateau, int largeurPlateau) {
        this.hauteur = hauteurPlateau;
        this.largeur = largeurPlateau;
        this.grilleCase = new Case[largeurPlateau][hauteurPlateau];

        for(int i  = 0; i < largeurPlateau; i++){
            for(int j  = 0; j < hauteurPlateau; j++){
                this.grilleCase[i][j] = new Case(i,j);
            }
        }
    }

    public Case[][] getGrilleCase() {
        return grilleCase;
    }
    
    public Case getCase(int x, int y)
    {
        return grilleCase[x][y];
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
