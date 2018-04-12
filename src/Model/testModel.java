package Model;
import Model.Plateau.Case;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        Grille g = new Grille(5,5);

        System.out.println("Test pièce");
        int[][] dispo1 = new int[][]{
                {1,1,0},
                {0,1,1}
        };
        Piece p = new Piece(g,dispo1);
        p.afficherDisposition();

        p.placement(0,0);

        Case[][] grille = g.getGrilleCase();
        for(Case[] ligne : grille){
            for(Case c: ligne){
                if(c.getPiece() == null){
                    System.out.print(c.getX()+ " "+ c.getY()+ " | ");
                } else{
                    System.out.print(" pp | ");
                }

            }
            System.out.println();
        }

        p.translation(-1,0);

        for(Case[] ligne : grille){
            for(Case c: ligne){
                if(c.getPiece() == null){
                    System.out.print(c.getX()+ " "+ c.getY()+ " | ");
                } else{
                    System.out.print(" pp | ");
                }

            }
            System.out.println();
        }
    }
}
