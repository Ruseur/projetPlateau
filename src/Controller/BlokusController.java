package Controller;

import Model.Board.Board;
import Model.Board.Grid;
import Model.Game.Blokus;
import Model.Player.Player;
import Model.Board.Piece;
import javafx.scene.paint.Color;
import view.BlokusView;

import java.util.*;


public class BlokusController extends GameController {

    private Blokus blokus;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Piece> pieces = new ArrayList<>();


    BlokusController(Board board){
        super(board);
        this.addPieces();

        ArrayList<Color> availableColors = new ArrayList<>();
        availableColors.add(Color.BLUE);
        availableColors.add(Color.GREEN);
        availableColors.add(Color.HOTPINK);
        availableColors.add(Color.RED);

        Grid grid = new Grid(20,20);
        Blokus blokus = new Blokus(grid);

        blokus.setPieces(this.pieces);

        this.blokus = blokus;

        this.blokus.addPlayer(new Player("Toto"));
        this.blokus.addPlayer(new Player("Tata"));
        this.blokus.addPlayer(new Player("Tutu"));
        this.blokus.addPlayer(new Player("Titi"));


        Random random = new Random();

        ArrayList<ArrayList<Piece>> playersPieces = new ArrayList<>();
        for(Player player : this.blokus.getPlayers()) {
            int index = random.nextInt(availableColors.size());

            Color color = availableColors.get(index);
            availableColors.remove(index);
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


    private void start() {


        this.players = this.blokus.getPlayers();
        this.blokus.setCurrentPlayer(this.players.get(0));

        this.blokus.setCurrentPlayerPieces(this.blokus.getPlayersPieces().get(0));

        this.blokus.setStatus("playing");
    }

    private void next() {
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

    public void gameEnd(){
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
                this.reset();
                this.start();
                break;
        }
    }
}

