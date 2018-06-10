package Model.Board;

import java.util.Observable;

public class Grid extends Observable {

    private int height, width;
    private Case[][] gridCase;

    public Grid(int width, int height) {
        this.setHeight(height);
        this.setWidth(width);
        this.gridCase = new Case[height][width];

        for(int i  = 0; i < height; i++){
            for(int j  = 0; j < width; j++){
                this.gridCase[i][j] = new Case(i,j);
            }
        }

    }

    public int deleteRow(){ //TODO améliorer en parcourant seulement les lignes bornées par la pièce venant d'etre posée
        int nbDeletedRows = 0;
        int y = 0;
        int x;

        while(y < gridCase.length){ //parcours de toutes les lignes
            x = 0;
            boolean filledRow = true;
            while(x < gridCase[0].length && filledRow){ //parcours de toutes les colonnes pou tester leur occupation
                filledRow = gridCase[y][x].getPiece() != null;
                x++;
            }
            if(filledRow){ //toutes les cases sont occupées
                nbDeletedRows++;
                for(int i = y; i > 0; i--){
                    gridCase[i] = gridCase[i-1];
                    for(int colUpdate = 0; colUpdate < gridCase[0].length; colUpdate++){
                        gridCase[i][colUpdate].setY(i);
                        this.updateCase(gridCase[i][colUpdate]);
                    }
                }
                Case[] newEmptyRow = new Case[gridCase[0].length];
                for(int i=0; i<newEmptyRow.length; i++){
                    newEmptyRow[i] = new Case(0,i);
                }
                gridCase[0] = newEmptyRow;
            }
            y++;
        }
        return nbDeletedRows;
    }

    public Case getCase(int y, int x){
        if(y >= 0 && y < gridCase.length && x >= 0 && x < gridCase[0].length){
            return gridCase[y][x];
        }else{
            return null;
        }
    }

    void updateCase(Case c){
        setChanged();
        notifyObservers(c);
    }

    public void clear() {
        for(int i = 0; i < this.getHeight(); i++){
            for(int j = 0; j < this.getWidth(); j++){
                this.gridCase[i][j] = new Case(i,j);
                setChanged();
                notifyObservers(this.gridCase[i][j]);
            }
        }
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    private void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    private void setWidth(int width) {
        this.width = width;
    }
}
