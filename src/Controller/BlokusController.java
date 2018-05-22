package Controller;

import Model.Jeu.Blokus;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import Model.Plateau.Plateau;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import view.BlokusView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


public class BlokusController extends GameController {

    private Blokus blokus;
    private Piece currentPiece;
    private Grille grille;
    private Piece nextPiece;
    private boolean perdu;
    private Timer timer;

    private int currentLevel;
    private int nbPieceLevel;


    public BlokusController(Plateau plateau){
        super(plateau);

        Grille grille = new Grille(20,20);
        Blokus blokus = new Blokus(grille);
        this.blokus = blokus;
        this.grille = grille;

        super.gameView = new BlokusView(this, blokus);
    }


    public void start() {
        this.blokus.setStatus("playing");
    }

    public void next() {
        // TODO
    }

    public void rotation() {
        // TODO
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

    public void finPartie(){
        System.out.println("Fin de partie");
        this.blokus.setStatus("finished");
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
        int[][] dispo = listePiece.get((int) rand);
        Color color = listeCouleur.get((int) rand);

        int id = 1;
        if (this.currentPiece != null) {
            id = this.currentPiece.getId() + 1;
        }

        return new Piece(id, dispo, color);
    }

    /**
     * Call by @BlokusView
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
                if(this.blokus.getStatus().equals("playing"))
                    this.translation("gauche");
                break;
            case "Right":
                if(this.blokus.getStatus().equals("playing"))
                    this.translation("droite");
                break;
            case "Up":
                if(this.blokus.getStatus().equals("playing"))
                    this.rotation();
                break;
            case "Down":
                if(this.blokus.getStatus().equals("playing"))
                    this.translation("bas");
                break;
        }
    }
}

