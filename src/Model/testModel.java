package Model;

import Model.Plateau.Case;
import Model.Plateau.Grille;
import Model.Plateau.Piece;

public class testModel {
    public testModel() {
    }

    public static void main(String[] args){
       testGrille();
       testPiece();
    }

    public static void testGrille(){
        System.out.println("Test grille");
        Grille g = new Grille(5,6);

        Case[][] grille = g.getGrilleCase();
        for(Case[] ligne : grille){
            for(Case c: ligne){
                System.out.print(c.getX()+ " "+ c.getY()+ " | ");
            }
            System.out.println();
        }
    }

    public static void testPiece(){
        System.out.println("Test pièce");
        int[][] dispo1 = new int[][]{
                {0,1},
                {0,1}
        };
        Piece p = new Piece(dispo1);
        p.afficherDisposition();
    }
}
