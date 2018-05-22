package Controller;

import Controller.service.TextParser;
import Model.Jeu.Tetris;
import Model.Joueur.Joueur;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import javafx.application.Platform;
import Model.Plateau.Plateau;
import jdk.nashorn.internal.ir.WhileNode;
import view.TetrisView;

import javafx.scene.paint.Color;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class TetrisController extends GameController {

    private Tetris jeu;
    private Piece currentPiece;
    private Grille grille;
    private Piece nextPiece;
    private boolean perdu;
    private Timer timer;

    private int currentLevel;
    private int nbPieceLevel;


    public TetrisController(Plateau plateau){
        super(plateau);

        Grille grille = new Grille(10,20);
        Tetris tetris = new Tetris(grille);
        this.jeu = tetris;
        this.perdu = false;
        this.grille = grille;

        this.jeu.setNextPieceGrille(new Grille(5,4));

        this.jeu.setLevel(1);
        this.nbPieceLevel = 0;

        this.timer = new Timer();

        super.gameView = new TetrisView(this, tetris);
    }


    public void start() {
        this.currentPiece = this.generationPiece();

        this.majNextPiece();

        this.currentPiece.setGrille(this.grille);

        this.currentPiece.placement(0, 4);


        this.jeu.setStatus("playing");
        timer.schedule(nextFrameTask(), 0, 1000/this.jeu.getLevel());


    }

    private void majNextPiece() {
        this.jeu.getNextPieceGrille().clear();

        this.nextPiece = this.generationPiece();
        this.nextPiece.setGrille(this.jeu.getNextPieceGrille());
        this.nextPiece.placement(1,1);


    }

    public void nextTurn() {

        if (!currentPiece.translation(-1, 0)) { //la pièce descend
            //si elle ne peut pas descendre
            this.suppressionLigne();

            this.currentPiece = this.nextPiece;
            this.majNextPiece();


            this.currentPiece.setGrille(this.grille);

            if(!this.currentPiece.placement(0, 3)){
                this.perdu = true;
            }
            else{
                this.gestionLevel();
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

    public void gestionLevel(){
        if(nbPieceLevel >= 10){
            nbPieceLevel =0;
            this.jeu.setLevel(this.jeu.getLevel() + 1);

            this.timer.cancel();
            this.timer = new Timer();
            timer.schedule(nextFrameTask(), 0, 1000/this.jeu.getLevel());
        }
        else{
            nbPieceLevel++;
        }
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
        int nbLignesSuppr = this.grille.suppressionLigne();
        if(nbLignesSuppr != 0){
            int points = (int) (10* Math.pow(nbLignesSuppr, 2));
            this.jeu.getJoueur().setScore(this.jeu.getJoueur().getScore()+points);
        }
    }

    public void finPartie(){
        System.out.println("Fin de partie");
        this.saveScore();
        this.jeu.setStatus("finished");
        this.reset();

    }

    public void reset(){

        //Grille grille = new Grille(10,20);
        this.grille.clear();
        this.perdu = false;

        this.jeu.getJoueur().setScore(0);
        this.jeu.setLevel(1);
        this.nbPieceLevel = 0;

        this.timer.cancel();
        this.timer = new Timer();
    }

    public void resume(){
        this.timer = new Timer();
        timer.schedule(nextFrameTask(), 0, 1000/this.jeu.getLevel());
        this.jeu.setStatus("playing");
    }

    public void pause(){
        timer.cancel();
        this.jeu.setStatus("paused");
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
     * Call by @TetrisView
     *
     * @param action determine which action user wants to do
     */
    public void command(String action) {
        switch (action) {
            case "Play":
                this.start();
                break;
            case "Resume":
                this.resume();
                break;
            case "Pause":
                this.pause();
                break;
            case "Reset":
                this.reset();
                this.start();
                break;
            case "Left":
                if(this.jeu.getStatus().equals("playing"))
                    this.translation("gauche");
                break;
            case "Right":
                if(this.jeu.getStatus().equals("playing"))
                    this.translation("droite");
                break;
            case "Up":
                if(this.jeu.getStatus().equals("playing"))
                    this.rotation();
                break;
            case "Down":
                if(this.jeu.getStatus().equals("playing"))
                    this.translation("bas");
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
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            finPartie();
                        }
                    });
                    cancel();
                }
            }
        };
    }

    public ArrayList<String> getSavedScores(){
        TextParser tp = new TextParser("scoresTetris.txt");
        return tp.readAll();
    }

    public void saveScore(){
        int currentScore = this.jeu.getJoueur().getScore();


        ArrayList<String> bestScores = this.getSavedScores();

        boolean isAdded = false;
        int i = 0;
        int maxIndex = bestScores.size();

        while(!isAdded){
            if(i == maxIndex) {
                bestScores.add(i, this.jeu.getJoueur().getNom()+":"+Integer.toString(currentScore));
                isAdded = true;
            } else {
                String[] score = bestScores.get(i).split(":");
                if (currentScore > Integer.parseInt(score[1])) {
                    bestScores.add(i, this.jeu.getJoueur().getNom() + ":" + Integer.toString(currentScore));
                    isAdded = true;
                } else {
                    i++;
                }
            }
        }

        TextParser tp = new TextParser("scoresTetris.txt");
        tp.write(bestScores);
    }

    public void setJoueur(String nom) {
        this.jeu.setJoueur(new Joueur(nom));
    }
}

