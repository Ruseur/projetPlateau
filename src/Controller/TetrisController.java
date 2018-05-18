package Controller;

import Model.Plateau.Case;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import javafx.application.Platform;
import view.TetrisView;

import javafx.scene.paint.Color;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class TetrisController extends GameController {

    private Piece currentPiece;
    private Grille grille;
    private Piece nextPiece;
    private boolean perdu;
    private Timer timer;

    public TetrisController() {

        //Initialiser la vue
        Grille grille = new Grille(10, 20);
        TetrisView tetrisView = new TetrisView(this, grille);
        this.perdu = false;
        this.grille = grille;
        super.gameView = tetrisView;

        this.timer = new Timer();
        this.start();
    }


    public void start() {
        this.currentPiece = this.generationPiece();
        this.currentPiece.setGrille(this.grille);

        this.currentPiece.placement(0, 3);

        timer.schedule(nextFrameTask(), 0, 400);


    }

    public void nextTurn() {

        if (!currentPiece.translation(-1, 0)) { //la pièce descend
            //si elle ne peut pas descendre
            this.suppressionLigne();

            this.currentPiece = this.generationPiece();
            this.currentPiece.setGrille(this.grille);

            if(!this.currentPiece.placement(0, 3)){
                this.perdu = true;
            }
        }

        /*for(Case[] ligne : this.grille.getGrilleCase()){
            for(Case c: ligne){
                int id=0;
                    if(c.getPiece() !=null){
                        id = c.getPiece().getId();
                    }
                    System.out.print("("+ id +")| ");


            }
            System.out.println();
        }*/
    }

    public void rotation() {

        if (!this.currentPiece.rotationHoraire()) {
            //decalage de la pièce pour qu'elle puisse tourner a droite du tableau
            int decalage = (this.currentPiece.getMaxY() - this.currentPiece.getMinY());
            this.currentPiece.translation(-(decalage) / 2, -decalage);
            this.currentPiece.rotationHoraire();
        }

    }

    public void translation(String direction) {
        switch (direction) {
            case "bas":
                this.currentPiece.translation(-1, 0);
                break;
            case "gauche":
                this.currentPiece.translation(0, -1);
                break;
            case "droite":
                this.currentPiece.translation(0, 1);
                break;
        }
    }

    public void suppressionLigne() {
        this.grille.suppressionLigne();
    }

    public void finPartie(){
        System.out.println("Fin de partie");
    }

    private Piece generationPiece() {
        Map<Integer, int[][]> listePiece = new HashMap<>();
        Map<Integer, Color> listeCouleur = new HashMap<>();
        listePiece.put(0,
                new int[][]{
                        {1,1,1,1}
                }
        );
        listePiece.put(1,
                new int[][]{
                        {1, 1},
                        {1, 1},
                }
        );
        listePiece.put(2,
                new int[][]{
                        {1, 1, 0},
                        {0, 1, 1}
                }
        );
        listePiece.put(3,
                new int[][]{
                        {0, 1, 1},
                        {1, 1, 0}
                }
        );
        listePiece.put(4,
                new int[][]{
                        {1, 0, 0},
                        {1, 1, 1}
                }
        );
        listePiece.put(5,
                new int[][]{
                        {0, 0, 1},
                        {1, 1, 1}
                }
        );
        listePiece.put(6,
                new int[][]{
                        {0, 1, 0},
                        {1, 1, 1}
                }
        );

        listeCouleur.put(0, Color.DARKCYAN);
        listeCouleur.put(1, Color.BLUE);
        listeCouleur.put(2, Color.MAGENTA);
        listeCouleur.put(3, Color.CYAN);
        listeCouleur.put(4, Color.CHARTREUSE);
        listeCouleur.put(5, Color.RED);
        listeCouleur.put(6, Color.YELLOW);

        double rand = ThreadLocalRandom.current().nextInt(0, listePiece.size());
        ;
        int[][] dispo = listePiece.get((int) rand);
        Color color = listeCouleur.get((int) rand);

        int id = 1;
        if (this.currentPiece != null) {
            id = this.currentPiece.getId() + 1;
        }
        /*if(dispo == null){
            System.out.println("DISPO NULLE");
            System.out.println(rand);
        }*/
        return new Piece(id, dispo, color);
    }


    /**
     * Call by @TetrisView
     *
     * @param action determine which action user wants to do
     */
    public void command(String action) {
        switch (action) {
            case "Play":
                this.start();
                break;
            case "Pause":
                // TODO
                break;
            case "Reset":
                this.start();
                break;
            case "Left":
                this.translation("gauche");
                break;
            case "Right":
                this.translation("droite");
                break;
            case "Up":
                this.rotation();
                break;
            case "Down":
                this.translation("bas");
                break;
            default:
                break;
        }
    }

    public TimerTask nextFrameTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (!perdu) { // condition pour vérifier si le joueur n'a pas perdu
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            nextTurn();
                        }
                    });
                } else {
                    finPartie();
                    cancel();
                }
            }
        };
    }
}

