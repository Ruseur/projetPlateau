package Model.Plateau;

import java.util.ArrayList;

public class Piece {

    private int id;
    private Grille grille;
    private int[][] disposition;
    private ArrayList<Case> listCase;

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Piece(Grille grille, int[][] disposition) {

        this.grille = grille;
        this.id = 1; //TODO a passer en param

        this.disposition = disposition;
        this.listCase = new ArrayList<>();

        this.maxX = 0;
        this.maxY = 0;
        this.minY = 2147483647;
        this.minX = 2147483647;
    }

    public ArrayList<Case> getListCase() {
        return listCase;
    }

    public void afficherDisposition(){
        for(int[] ligne: disposition){
            for(int i: ligne){
                System.out.print(i +" | ");
            }
            System.out.println();
        }
    }

    //après construction, la pièce va être placée sur la grille de plateau
    //v1 => on construit la pièce avec comme point d'origine dans sa disposition en haut à gauche TODO
    public void placement(int yOrig, int xOrig){

        int y = 0;
        int x = 0;
        boolean controlBorneY = y>= 0 && y+yOrig <= grille.getGrilleCase().length;
        boolean controlBorneX = x >= 0 && x+xOrig <= grille.getGrilleCase()[0].length;

        while(y < this.disposition.length && controlBorneY){
            x = 0;
            while(x < disposition[0].length && controlBorneX){
                majBornes(y,x);

                if(disposition[y][x] == 1){
                    grille.getGrilleCase()[y][x].setPiece(this);
                    listCase.add(grille.getGrilleCase()[y][x]);
                }
                x++;
            }
            y++;
        }
        if(controlBorneY && controlBorneX){ //la piece a pu se placer en entier
            grille.addPiece(this);
        }else{ //la piece n'a pas pu se placer en entier on libère les pièces
            this.desattribution();
            System.out.println("Pièce n'a pas pu être placée");
        }
    }



    public void translation(int y, int x){
        int nbCases = this.getListCase().size();

        this.desattribution();

        ArrayList<Case> newCases = new ArrayList<>();
        for (int i=0; i<nbCases; i++){
            //on récupère la case d'en dessous et on se l'attribue
            Case c = this.grille.getGrilleCase()[this.getListCase().get(i).getY()-y][this.getListCase().get(i).getX()-x];
            c.setPiece(this);
            newCases.add(c);
        }
        this.listCase = newCases;

    }

    private void majBornes(int y, int x) {
        if(y < minY){
            minY = y;
        }
        if(x < minX){
            minX = x;
        }
        if(y > maxY){
            maxY = y;
        }
        if(x > maxX){
            maxX = x;
        }
    }

    public int getId() {
        return id;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    private void desattribution(){
        for(Case c: this.getListCase()){
            c.raz();
        }
    }
}
