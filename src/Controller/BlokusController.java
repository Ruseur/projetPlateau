package Controller;

import Model.Jeu.Blokus;
import Model.Joueur.Joueur;
import Model.Plateau.Grille;
import Model.Plateau.Piece;
import Model.Plateau.Plateau;
import javafx.scene.paint.Color;
import view.BlokusView;

import java.util.*;


public class BlokusController extends GameController {

    private Blokus blokus;
    private Grille grille;
    private ArrayList<Joueur> players = new ArrayList<>();
    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<Color> availableColors = new ArrayList<>();



    public BlokusController(Plateau plateau){
        super(plateau);
        this.addPieces();

        availableColors.add(Color.BLUE);
        availableColors.add(Color.GREEN);
        availableColors.add(Color.HOTPINK);
        availableColors.add(Color.RED);

        Grille grille = new Grille(20,20);
        Blokus blokus = new Blokus(grille);

        blokus.setPieces(this.pieces);

        this.blokus = blokus;
        this.grille = grille;

        this.blokus.addPlayer(new Joueur("Toto"));
        this.blokus.addPlayer(new Joueur("Tata"));
        this.blokus.addPlayer(new Joueur("Tutu"));
        this.blokus.addPlayer(new Joueur("Titi"));


        Random random = new Random();

        ArrayList<ArrayList<Piece>> playersPieces = new ArrayList<>();
        for(Joueur player : this.blokus.getPlayers()) {
            int index = random.nextInt(this.availableColors.size());

            Color color = this.availableColors.get(index);
            this.availableColors.remove(index);
            player.setColor(color);
            playersPieces.add(this.getPiecesWithColor(color));
        }
        this.blokus.setPlayersPieces(playersPieces);

        super.gameView = new BlokusView(this, blokus);
    }

    @Override
    public void reset() {

    }


    private ArrayList<Piece> getPiecesWithColor(Color color) {
        ArrayList<Piece> playerPiece = new ArrayList<>();


        for(Piece piece: this.pieces) {

            Piece currentPiece = new Piece(piece.getId(),piece.getDisposition(),color);
            playerPiece.add(currentPiece);

        }

        return playerPiece;
    }

    private void addPieces() {
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
        this.pieces.add(new Piece(7,
                        new int[][]{
                                {1, 0, 0},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(8,
                        new int[][]{
                                {0, 0, 1},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(9,
                        new int[][]{
                                {0, 1, 0},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(10,
                        new int[][]{
                                {0, 0, 1},
                                {1, 1, 1}
                        },
                        Color.RED
                )
        );
        this.pieces.add(new Piece(11,
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

        this.blokus.setCurrentPlayerPieces(this.blokus.getPlayersPieces().get(0));

        this.blokus.setStatus("playing");
    }

    public void next() {
        int nextPlayerIndex = this.blokus.getNextPlayerIndex();
        this.blokus.setCurrentPlayer(this.blokus.getPlayers().get(nextPlayerIndex));
        this.blokus.setCurrentPlayerPieces(this.blokus.getPlayersPieces().get(nextPlayerIndex));
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
            case "Next":
                this.next();
                break;
            case "Pause":
                // TODO
                break;
            case "Reset":
                this.start();
                break;
        }
    }
}

