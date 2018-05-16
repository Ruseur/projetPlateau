package Model;
import Model.Plateau.Case;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class testModel {
    public testModel() {
    }

    public static void main(String[] args){
       //testGrille();
       testPiece();
       //testRotation();

        /*Grille g = new Grille(5,6);
        int[][] dispo1 = new int[][]{
                {1,1,0},
                {0,1,1}
        };
        Piece p = new Piece(g,dispo1);

        Case c1 = new Case(1,1);
        Case c2 = new Case(2,2);
        Case c3 = new Case(3,3);
        Case c4 = new Case(4,4);
        ArrayList<Case> list1 = new ArrayList<Case>();
        list1.add(c1);
        list1.add(c2);
        list1.add(c3);
        list1.add(c4);
        ArrayList<Case> list2 = new ArrayList<Case>();
        list2.add(c2);
        list2.add(c3);

        p.anciennesCase(list1, list2);*/

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

        p.placement(4,0);

        Case[][] grille = g.getGrilleCase();
        for(Case[] ligne : grille){
            for(Case c: ligne){
                if(c.getPiece() == null){
                    int id=0;
                    if(c.getPiece() !=null){
                        id = c.getPiece().getId();
                    }
                    System.out.print("("+ id +")| ");
                } else{
                    System.out.print(" pp | ");
                }

            }
            System.out.println();
        }

        System.out.println(p.rotationHoraire());

        for(Case[] ligne : grille){
            for(Case c: ligne){
                if(c.getPiece() == null){
                    int id=0;
                    if(c.getPiece() !=null){
                        id = c.getPiece().getId();
                    }
                    System.out.print("("+ id +")| ");
                } else{
                    System.out.print(" pp | ");
                }

            }
            System.out.println();
        }
         p.rotationHoraire();

        for(Case[] ligne : grille){
            for(Case c: ligne){
                if(c.getPiece() == null){
                    int id=0;
                    if(c.getPiece() !=null){
                        id = c.getPiece().getId();
                    }
                    System.out.print("("+ id +")| ");
                } else{
                    System.out.print(" pp | ");
                }

            }
            System.out.println();
        }
    }

    public static void testRotation(){
        Grille g = new Grille(5,5);

        int[][] dispo1 = new int[][]{
                {0,1,},
                {1,1,}
        };
        Piece p = new Piece(g,dispo1);
        p.afficherDisposition();
        p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();p.rotationHoraire();
        p.afficherDisposition();
    }
}
