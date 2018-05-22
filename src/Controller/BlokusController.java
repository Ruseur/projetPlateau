package Controller;

import Model.Jeu.Blokus;
import Model.Joueur.Joueur;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import Model.Plateau.Plateau;
import javafx.scene.paint.Color;
import view.BlokusView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class BlokusController extends GameController {

    private Blokus blokus;
    private Grille grille;
    private ArrayList<Joueur> players = new ArrayList<>();
    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<Grille> gridByPlayers = new ArrayList<>();
    private ArrayList<Color> availableColors = new ArrayList<>();



    public BlokusController(Plateau plateau){
        super(plateau);
        this.setPieces();

        availableColors.add(Color.LEMONCHIFFON);
        availableColors.add(Color.SANDYBROWN);
        availableColors.add(Color.CHARTREUSE);
        availableColors.add(Color.NAVAJOWHITE);

        Grille grille = new Grille(20,20);
        Blokus blokus = new Blokus(grille);

        blokus.setPieces(this.pieces);

        this.blokus = blokus;
        this.grille = grille;

        this.blokus.addJoueur(new Joueur("Toto"));
        this.blokus.addJoueur(new Joueur("Tata"));
        this.blokus.addJoueur(new Joueur("Tutu"));
        this.blokus.addJoueur(new Joueur("Titi"));

        for(Joueur player : this.blokus.getPlayers()) {
            gridByPlayers.add(this.getPlayerGrid(player));
        }


        super.gameView = new BlokusView(this, blokus);
    }

    private Grille getPlayerGrid(Joueur player) {
        Grille grid = new Grille(10,10);
        Random random = new Random();
        int index = random.nextInt(this.availableColors.size());

        Color color = this.availableColors.get(index);
        this.availableColors.remove(index);

        for(Piece piece: this.pieces) {

            grid.addPiece(new Piece(piece.getId(),piece.getDisposition(),color));

        }

        return grid;
    }

    private void setPieces() {
        this.pieces.add(new Piece(0,
                        new int[][]{
                                {1,1,1,1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(1,
                        new int[][]{
                                {1, 1},
                                {1, 1},
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(2,
                        new int[][]{
                                {1, 1, 0},
                                {0, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(3,
                        new int[][]{
                                {0, 1, 1},
                                {1, 1, 0}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(4,
                        new int[][]{
                                {1, 0, 0},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(5,
                        new int[][]{
                                {0, 0, 1},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(6,
                        new int[][]{
                                {0, 1, 0},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
    }


    public void start() {


        this.players = this.blokus.getPlayers();
        this.blokus.setCurrentPlayer(this.players.get(0));
        this.blokus.setCurrentGridPlayer(this.gridByPlayers.get(0));
        this.blokus.setStatus("playing");
    }

    public void next() {
        // TODO
    }

    public void rotation() {
        // TODO
    }

    public void translation(String direction) {
        // TODO
    }

    public void finPartie(){
        System.out.println("Fin de partie");
        this.blokus.setStatus("finished");
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

