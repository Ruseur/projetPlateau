package Controller;

import Model.Plateau.Case;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import view.GrillePanel;
import view.TetrisView;


import javafx.scene.paint.Color;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TetrisController extends GameController {

    private Piece currentPiece;
    private Grille grille;
    private Piece nextPiece;
    private boolean perdu;

    public TetrisController(){

        //Initialiser la vue
        Grille grille = new Grille(10,20);
        TetrisView tetrisView = new TetrisView(this, grille);
        this.perdu = false;
        this.grille = grille;
        super.gameView = tetrisView;
    }


    public void start(){
        this.nextTurn();
        /*while(!perdu){

        }*/
        int i = 0;
        //this.currentPiece.rotationHoraire();
        while(i < 19){
            this.currentPiece.translation(-1,0);
            /*try{
                TimeUnit.SECONDS.sleep(1);
            }catch(Exception e){
                System.out.println("Erreur: "+e.getMessage());
            }*/
            i++;
        }
        for(Case[] ligne : this.grille.getGrilleCase()){
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

    public void nextTurn(){
        this.currentPiece = this.generationPiece();
        this.currentPiece.setGrille(this.grille);

        this.currentPiece.placement(0,3);

        /*for(Case[] ligne : this.grille.getGrilleCase()) {
            for (Case c : ligne) {
                System.out.println(c.getColor().toString());
            }
            System.out.println();
        }*/
    }

    public void rotation(){

    }

    public void translation(String direction){
        switch (direction){
            case "bas":
                break;
            case "gauche":
                break;
            case "droite":
                break;
        }
    }

    public void suppressionLigne(){

    }

    private Piece generationPiece(){
        Map<Integer, int[][]> listePiece = new HashMap<>();
        Map<Integer, Color> listeCouleur = new HashMap<>();
        listePiece.put(0,
                new int[][]{
                {1},
                {1},
                {1},
                {1}
                }
        );
        listePiece.put(1,
                new int[][]{
                        {1,1},
                        {1,1},
                }
        );
        listePiece.put(2,
                new int[][]{
                        {1,1,0},
                        {0,1,1}
                }
        );
        listePiece.put(3,
                new int[][]{
                        {0,1,1},
                        {1,1,0}
                }
        );
        listePiece.put(4,
                new int[][]{
                        {1,0,0},
                        {1,1,1}
                }
        );
        listePiece.put(5,
                new int[][]{
                        {0,0,1},
                        {1,1,1}
                }
        );
        listePiece.put(6,
                new int[][]{
                        {0,1,0},
                        {1,1,1}
                }
        );

        listeCouleur.put(0, Color.AQUA);
        listeCouleur.put(1, Color.BLUE);
        listeCouleur.put(2, Color.MAGENTA);
        listeCouleur.put(3, Color.CYAN);
        listeCouleur.put(4, Color.CHARTREUSE);
        listeCouleur.put(5, Color.RED);
        listeCouleur.put(6, Color.YELLOW);

        double rand = ThreadLocalRandom.current().nextInt(0, listePiece.size());;
        int[][] dispo =  listePiece.get((int) rand);
        Color color = listeCouleur.get((int) rand);

        int id = 1;
        if(this.currentPiece != null){
            id = this.currentPiece.getId()+1;
        }
        /*if(dispo == null){
            System.out.println("DISPO NULLE");
            System.out.println(rand);
        }*/
        return new Piece(id , dispo, color);
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button){
            if(((Button) event.getSource()).getText().equals("Play"))
                this.start();
        }
    }
}
