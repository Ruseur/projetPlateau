package Model.Board;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;

public class Piece extends Observable {

    private int id;
    private Grid grid;
    private int[][] disposition;
    private ArrayList<Case> listCase;
    private Color color;

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;


    public Piece(int id, int[][] disposition) {
        this(id, disposition, Color.BLUE);
    }

    public Piece(int id, int[][] disposition, Color color) {

        this.grid = null;
        this.id = id;

        this.disposition = disposition;
        this.listCase = new ArrayList<>();

        this.color = color;

        this.maxX = 0;
        this.maxY = 0;
        this.minY = 2147483647;
        this.minX = 2147483647;

    }

    public void updatePiece(){
        for(Case c: this.listCase){
            this.grid.updateCase(c);
        }
    }



    public ArrayList<Case> getListCase() {
        return listCase;
    }

    //après construction, la pièce va être placée sur la grid de board
    //v1 => on construit la pièce avec comme point d'origine dans sa disposition en haut à gauche TODO
    public boolean place(int yOrig, int xOrig) {

        boolean placed = true;
        int y = 0;
        int x;
        ArrayList<Case> newListCase = new ArrayList<>();

            while (y < this.disposition.length && placed) {
                x = 0;
                while (x < disposition[0].length && placed) {
                    if (disposition[y][x] == 1) {
                        Case c = this.grid.getCase(yOrig + y, xOrig + x);
                        if (c != null) {
                            placed = c.setPiece(this);
                            newListCase.add(c);
                        } else {
                            placed = false;
                        }
                    }
                    x++;
                }
                y++;
            }
        if (placed) { //la piece a pu se placer en entier.
            //removePieces de toutes les cases qui ne sont pas en commun
            /*ArrayList<Case> saveAncienneCase = this.oldCases(this.getListCase(), newListCase);
            this.removePieces(saveAncienneCase);
            this.listCase = newListCase;
            updateLandmarks();
            //grid.addPiece(this); //TODO attention lors de la rotation plusieurs ajouts sont possible a modifier
            this.updatePiece();*/

            this.removePieces(this.oldCases(this.getListCase(), newListCase));
            this.listCase = newListCase;
            updateLandmarks();
            this.updatePiece();
        } else { //la piece n'a pas pu se placer en entier on libère les pièces
            this.removePieces(this.oldCases(newListCase, this.listCase));
            /*System.out.println("Pièce n'a pas pu être placée");*/
        }
        return placed;
    }


    public boolean translation(int y, int x) {
        int nbCases = this.getListCase().size();

        ArrayList<Case> newCases = new ArrayList<>();
        boolean placementReussi = true;
        int i = 0;
        while (i < nbCases && placementReussi) {
            //on récupère la case d'en dessous et on se l'attribue
            Case c = this.grid.getCase(this.getListCase().get(i).getY() - y, this.getListCase().get(i).getX() + x);
            if (c != null) {
                /*System.out.println("Case trouvée y:" + c.getY() + " x:"+c.getX());*/
                placementReussi = c.setPiece(this);
                newCases.add(c);
            }else{
                placementReussi = false;
            }
            i++;
        }
        if (placementReussi) {
            this.removePieces(this.oldCases(this.getListCase(), newCases));
            this.listCase = newCases;
            updateLandmarks();
            this.updatePiece();
            return true;
        } else {
            this.removePieces(this.oldCases(newCases, this.listCase));
            /*System.out.println("La pièce n'a pas pu être translatée");*/
            return false;
        }
    }

    public boolean testPlacement(int yOrig, int xOrig) {
        while(!place(yOrig++,xOrig++)) {
            yOrig = yOrig+2;
        }
        return true;
    }

    public boolean clockwiseRotation() {
        //transformation de la disposition
        int widthDisposition = this.disposition[0].length;
        int heightDisposition = this.disposition.length;

        int[][] newDisposition = new int[widthDisposition][heightDisposition]; //inversion hauteur largeur
        for (int y = 0; y < this.disposition.length; y++) {
            for (int x = 0; x < this.disposition[0].length; x++) {
                newDisposition[x][newDisposition[0].length - 1 - y] = this.disposition[y][x];
            }
        }

        int[][] oldDisposition = this.disposition;
        this.disposition = newDisposition; //nouvelle dispo necessaire au test de place de la pièce


        if (place(this.minY, this.minX)) {
            return true;
        } else {
            this.disposition = oldDisposition; //rollback à l'ancienne dispo
            return false;
        }
    }

    public boolean fusion() {
        return false;
    }

    public boolean partialDelete() {
        return false;
    }

    private void updateLandmarks() {
        this.minX = this.listCase.get(0).getX();
        this.minY = this.listCase.get(0).getY();
        for (Case c : this.listCase) {
            int x = c.getX();
            int y = c.getY();
            if (y > this.maxY) {
                this.maxY = y;
            }
            if (y < this.minY) {
                this.minY = y;
            }
            if (x > this.maxX) {
                this.maxX = x;
            }
            if (x < this.minX) {
                this.minX = x;
            }
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

    private void removePieces(ArrayList<Case> listCase) {
        for (Case c : listCase) {
            if(c.getPiece().getId() == this.getId()){
                c.raz();
                this.grid.updateCase(c);
            }
        }
    }

    private ArrayList<Case> oldCases(ArrayList<Case> ancienneListe, ArrayList<Case> nouvelleListe) {
        // Prepare an intersection
        ArrayList<Case> intersection = new ArrayList<>(ancienneListe);
        intersection.retainAll(nouvelleListe);
        // Subtract the intersection from the union
        ancienneListe.removeAll(intersection);

        return ancienneListe;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Color getColor() {
        return color;
    }

    public int[][] getDisposition() {return disposition;}
}
