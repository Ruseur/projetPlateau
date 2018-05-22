package Model.Plateau;

import java.util.Observable;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Grille extends Observable {

    private int hauteur, largeur;
    private Case[][] grilleCase;
    ArrayList<Piece> listePieces;

    public Grille(int largeurPlateau, int hauteurPlateau) {
        this.setHauteur(hauteurPlateau);
        this.setLargeur(largeurPlateau);
        this.grilleCase = new Case[hauteurPlateau][largeurPlateau];

        for(int i  = 0; i < hauteurPlateau; i++){
            for(int j  = 0; j < largeurPlateau; j++){
                this.grilleCase[i][j] = new Case(i,j);
            }
        }

        this.listePieces = new ArrayList<>();
    }

    public int suppressionLigne(){ //TODO améliorer en parcourant seulement les lignes bornées par la pièce venant d'etre posée
        int nbLignesSuppr = 0;
        int y = 0;
        int x;

        while(y < grilleCase.length){ //parcours de toutes les lignes
            x = 0;
            boolean ligneOccupee = true;
            while(x < grilleCase[0].length && ligneOccupee){ //parcours de toutes les colonnes pou tester leur occupation
                ligneOccupee = grilleCase[y][x].getPiece() != null;
                x++;
            }
            if(ligneOccupee){ //toutes les cases sont occupées
                nbLignesSuppr++;
                for(int i = y; i > 0; i--){
                    grilleCase[i] = grilleCase[i-1];
                    for(int colUpdate = 0; colUpdate < grilleCase[0].length; colUpdate++){
                        grilleCase[i][colUpdate].setY(i);
                        this.updateCase(grilleCase[i][colUpdate]);
                    }
                }
                Case[] nouvelleLigneVide = new Case[grilleCase[0].length];
                for(int i=0; i<nouvelleLigneVide.length; i++){
                    nouvelleLigneVide[i] = new Case(0,i);
                }
                grilleCase[0] = nouvelleLigneVide;
            }
            y++;
        }
        return nbLignesSuppr;
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

    public void updateCase(Case c){
        setChanged();
        notifyObservers(c);
    }

    public void clear() {
        for(int i  = 0; i < this.getHauteur(); i++){
            for(int j  = 0; j < this.getLargeur(); j++){
                this.grilleCase[i][j] = new Case(i,j);
                setChanged();
                notifyObservers(this.grilleCase[i][j]);
            }
        }
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
