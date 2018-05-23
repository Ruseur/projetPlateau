package Controller;

import Controller.service.TextParser;
import Model.Game.Tetris;
import Model.Player.Player;
import Model.Board.Grid;
import Model.Board.Piece;
import Model.Board.Board;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import view.TetrisView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class TetrisController extends GameController {

    private Tetris game;
    private Piece currentPiece;
    private Piece nextPiece;
    private boolean loose;
    private Timer timer;

    private int nbPieceLevel;


    public TetrisController(Board board){
        super(board);

        Grid grid = new Grid(10,20);
        Tetris tetris = new Tetris(grid);
        this.game = tetris;
        this.loose = false;

        this.game.setNextPieceGrid(new Grid(5,4));

        this.game.setLevel(1);
        this.nbPieceLevel = 0;

        this.timer = new Timer();

        super.gameView = new TetrisView(this, tetris);
    }


    public void start() {
        this.currentPiece = this.getRandomPiece();

        this.updateNextPiece();

        this.currentPiece.setGrid(this.game.getGrid());

        this.currentPiece.place(0, 4);


        this.game.setStatus("playing");
        timer.schedule(nextFrameTask(), 0, 1000/this.game.getLevel());


    }

    private void updateNextPiece() {
        this.game.getNextPieceGrid().clear();

        this.nextPiece = this.getRandomPiece();
        this.nextPiece.setGrid(this.game.getNextPieceGrid());
        this.nextPiece.place(1,1);


    }

    private void nextTurn() {

        if (!currentPiece.translation(-1, 0)) { //la pièce descend
            //si elle ne peut pas descendre
            this.deleteRow();

            this.currentPiece = this.nextPiece;
            this.updateNextPiece();


            this.currentPiece.setGrid(this.game.getGrid());

            if(!this.currentPiece.place(0, 3)){
                this.loose = true;
            }
            else{
                this.updateLevel();
            }
        }

        /*for(Case[] ligne : this.game.getGrid().getGrilleCase()){
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

    private void updateLevel(){
        if(nbPieceLevel >= 10){
            nbPieceLevel =0;
            this.game.setLevel(this.game.getLevel() + 1);

            this.timer.cancel();
            this.timer = new Timer();
            timer.schedule(nextFrameTask(), 0, 1000/this.game.getLevel());
        }
        else{
            nbPieceLevel++;
        }
    }

    private void rotation() {

        if (!this.currentPiece.clockwiseRotation()) {
            //decalage de la pièce pour qu'elle puisse tourner a droite du tableau
            int decalage = (this.currentPiece.getMaxY() - this.currentPiece.getMinY());
            this.currentPiece.translation(-(decalage) / 2, -decalage);
            this.currentPiece.clockwiseRotation();
        }

    }

    private void translation(String direction) {
        switch (direction) {
            case "down":
                this.currentPiece.translation(-1, 0);
                break;
            case "left":
                this.currentPiece.translation(0, -1);
                break;
            case "right":
                this.currentPiece.translation(0, 1);
                break;
        }
    }

    private void deleteRow() {
        int nbDeletedRows = this.game.getGrid().deleteRow();
        if(nbDeletedRows != 0){
            int points = (int) (10* Math.pow(nbDeletedRows, 2));
            this.game.getPlayer().setScore(this.game.getPlayer().getScore()+points);
        }
    }

    private void gameEnd(){
        if(this.game.getPlayer().getScore() > 0)
            this.saveScore();
        this.reset();
        this.game.setStatus("finished");
    }

    public void reset(){

        //Grid grille = new Grid(10,20);
        this.game.getGrid().clear();
        this.loose = false;

        if(this.game.getPlayer() != null)
            this.game.getPlayer().setScore(0);

        this.game.setLevel(1);
        this.nbPieceLevel = 0;

        this.timer.cancel();
        this.timer = new Timer();
    }

    private void resume(){
        this.timer = new Timer();
        timer.schedule(nextFrameTask(), 0, 1000/this.game.getLevel());
        this.game.setStatus("playing");
    }

    private void pause(){
        timer.cancel();
        this.game.setStatus("paused");
    }

    private Piece getRandomPiece() {
        Map<Integer, int[][]> pieces = new HashMap<>();
        Map<Integer, Color> colorList = new HashMap<>();
        pieces.put(0,
                new int[][]{
                        {1,1,1,1}
                }
        );
        pieces.put(1,
                new int[][]{
                        {1, 1},
                        {1, 1},
                }
        );
        pieces.put(2,
                new int[][]{
                        {1, 1, 0},
                        {0, 1, 1}
                }
        );
        pieces.put(3,
                new int[][]{
                        {0, 1, 1},
                        {1, 1, 0}
                }
        );
        pieces.put(4,
                new int[][]{
                        {1, 0, 0},
                        {1, 1, 1}
                }
        );
        pieces.put(5,
                new int[][]{
                        {0, 0, 1},
                        {1, 1, 1}
                }
        );
        pieces.put(6,
                new int[][]{
                        {0, 1, 0},
                        {1, 1, 1}
                }
        );

        colorList.put(0, Color.DARKCYAN);
        colorList.put(1, Color.BLUE);
        colorList.put(2, Color.MAGENTA);
        colorList.put(3, Color.CYAN);
        colorList.put(4, Color.CHARTREUSE);
        colorList.put(5, Color.RED);
        colorList.put(6, Color.YELLOW);


        double rand = ThreadLocalRandom.current().nextInt(0, pieces.size());
        int[][] dispo = pieces.get((int) rand);
        Color color = colorList.get((int) rand);

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
                if(this.game.getStatus().equals("playing"))
                    this.translation("left");
                break;
            case "Right":
                if(this.game.getStatus().equals("playing"))
                    this.translation("right");
                break;
            case "Up":
                if(this.game.getStatus().equals("playing"))
                    this.rotation();
                break;
            case "Down":
                if(this.game.getStatus().equals("playing"))
                    this.translation("down");
                break;
        }
    }

    public TimerTask nextFrameTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (!loose) { // condition pour vérifier si le joueur n'a pas loose
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
                            gameEnd();
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
        int currentScore = this.game.getPlayer().getScore();


        ArrayList<String> bestScores = this.getSavedScores();

        boolean isAdded = false;
        int i = 0;
        int maxIndex = bestScores.size();

        while(!isAdded){
            if(i == maxIndex) {
                bestScores.add(i, this.game.getPlayer().getName()+":"+Integer.toString(currentScore));
                isAdded = true;
            } else {
                String[] score = bestScores.get(i).split(":");
                if (currentScore > Integer.parseInt(score[1])) {
                    bestScores.add(i, this.game.getPlayer().getName() + ":" + Integer.toString(currentScore));
                    isAdded = true;
                } else {
                    i++;
                }
            }
        }

        TextParser tp = new TextParser("scoresTetris.txt");
        tp.write(bestScores);
    }

    public void setPlayer(String name) {
        this.game.setPlayer(new Player(name));
    }
}

