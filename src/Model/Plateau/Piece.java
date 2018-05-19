package Model.Plateau;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Piece {

    private int id;
    private Grille grille;
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

        this.grille = null;
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
            this.grille.updateCase(c);
        }
    }



    public ArrayList<Case> getListCase() {
        return listCase;
    }

    public void afficherDisposition() {
        for (int[] ligne : disposition) {
            for (int i : ligne) {
                System.out.print(i + " | ");
            }
            System.out.println();
        }
    }

    //après construction, la pièce va être placée sur la grille de plateau
    //v1 => on construit la pièce avec comme point d'origine dans sa disposition en haut à gauche TODO
    public boolean placement(int yOrig, int xOrig) {

        boolean placementReussi = true;
        int y = 0;
        int x;
        ArrayList<Case> newListCase = new ArrayList<>();

            while (y < this.disposition.length && placementReussi) {
                x = 0;
                while (x < disposition[0].length && placementReussi) {
                    if (disposition[y][x] == 1) {
                        Case c = this.grille.getCase(yOrig + y, xOrig + x);
                        if (c != null) {
                            placementReussi = c.setPiece(this);
                            newListCase.add(c);
                        } else {
                            placementReussi = false;
                        }
                    }
                    x++;
                }
                y++;
            }
        if (placementReussi) { //la piece a pu se placer en entier.
            //desattribution de toutes les cases qui ne sont pas en commun
            /*ArrayList<Case> saveAncienneCase = this.anciennesCase(this.getListCase(), newListCase);
            this.desattribution(saveAncienneCase);
            this.listCase = newListCase;
            majBornes();
            //grille.addPiece(this); //TODO attention lors de la rotation plusieurs ajouts sont possible a modifier
            this.updatePiece();*/

            this.desattribution(this.anciennesCase(this.getListCase(), newListCase));
            this.listCase = newListCase;
            majBornes();
            this.updatePiece();
        } else { //la piece n'a pas pu se placer en entier on libère les pièces
            this.desattribution(this.anciennesCase(newListCase, this.listCase));
            System.out.println("Pièce n'a pas pu être placée");
        }
        return placementReussi;
    }


    public boolean translation(int y, int x) {
        int nbCases = this.getListCase().size();

        ArrayList<Case> newCases = new ArrayList<>();
        boolean placementReussi = true;
        int i = 0;
        while (i < nbCases && placementReussi) {
            //on récupère la case d'en dessous et on se l'attribue
            Case c = this.grille.getCase(this.getListCase().get(i).getY() - y, this.getListCase().get(i).getX() + x);
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
            this.desattribution(this.anciennesCase(this.getListCase(), newCases));
            this.listCase = newCases;
            majBornes();
            this.updatePiece();
            return true;
        } else {
            this.desattribution(this.anciennesCase(newCases, this.listCase));
            System.out.println("La pièce n'a pas pu être translatée");
            return false;
        }
    }

    public boolean rotationHoraire() {
        //transformation de la disposition
        int largeurDispo = this.disposition[0].length;
        int hauteurDispo = this.disposition.length;

        int[][] newDispo = new int[largeurDispo][hauteurDispo]; //inversion hauteur largeur
        for (int y = 0; y < this.disposition.length; y++) {
            for (int x = 0; x < this.disposition[0].length; x++) {
                newDispo[x][newDispo[0].length - 1 - y] = this.disposition[y][x];
            }
        }

        int[][] ancienneDispo = this.disposition;
        this.disposition = newDispo; //nouvelle dispo necessaire au test de placement de la pièce


        if (placement(this.minY, this.minX)) {
            return true;
        } else {
            this.disposition = ancienneDispo; //rollback à l'ancienne dispo
            return false;
        }
    }

    public boolean fusion() {
        return false;
    }

    public boolean suppressionPartielle() {
        return false;
    }

    private void majBornes() {
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

    private void desattribution(ArrayList<Case> listCase) {
        for (Case c : listCase) {
            if(c.getPiece().getId() == this.getId()){
                c.raz();
                this.grille.updateCase(c);
            }
        }
    }

    public ArrayList<Case> anciennesCase(ArrayList<Case> ancienneListe, ArrayList<Case> nouvelleListe) {
        // Prepare an intersection
        ArrayList<Case> intersection = new ArrayList<>(ancienneListe);
        intersection.retainAll(nouvelleListe);
        // Subtract the intersection from the union
        ancienneListe.removeAll(intersection);

        return ancienneListe;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public Color getColor() {
        return color;
    }
}
