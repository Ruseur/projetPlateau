package Controller;

import Model.Plateau.Grille;
import Model.Plateau.Piece;
import view.TetrisView;

import java.util.HashMap;
import java.util.Map;

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
        this.currentPiece.rotationHoraire();
        while(i < 5){
            this.currentPiece.translation(-1,0);
            /*try{
                TimeUnit.SECONDS.sleep(1);
            }catch(Exception e){
                System.out.println("Erreur: "+e.getMessage());
            }*/
            i++;
        }
        /*for(Case[] ligne : this.grille.getGrilleCase()){
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
        }*/
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
        this.currentPiece.rotationHoraire();
    }

    public void translation(String direction){
        switch (direction){
            case "bas":
                this.currentPiece.translation(-1,0);
                break;
            case "gauche":
                this.currentPiece.translation(0,-1);
                break;
            case "droite":
                this.currentPiece.translation(0,1);
                break;
        }
    }

    public void suppressionLigne(){

    }

    private Piece generationPiece(){
        Map<Integer, int[][]> listePiece = new HashMap<>();
        listePiece.put(1,
                new int[][]{
                {1},
                {1},
                {1},
                {1}
                }
        );
        listePiece.put(2,
                new int[][]{
                        {1,1},
                        {1,1},
                }
        );
        listePiece.put(3,
                new int[][]{
                        {1,1,0},
                        {0,1,1}
                }
        );
        listePiece.put(4,
                new int[][]{
                        {0,1,1},
                        {1,1,0}
                }
        );
        listePiece.put(5,
                new int[][]{
                        {1,0,0},
                        {1,1,1}
                }
        );
        listePiece.put(6,
                new int[][]{
                        {0,0,1},
                        {1,1,1}
                }
        );

        double rand = Math.random()*6;
        int[][] dispo =  listePiece.get((int) rand);
        return new Piece(dispo);
    }


    /**
     * Call by @TetrisView
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
}
