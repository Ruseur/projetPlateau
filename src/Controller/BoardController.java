package Controller;

import Model.Board.Board;
import view.BoardView;
import view.DefaultView;

public class BoardController {
    private BoardView boardView;
    private GameController gameController;
    private Board board;

    public BoardController() {
        this.board = new Board();
        this.boardView = new BoardView(this, this.board);
    }

    public void switchGame(String gameName){
        if(this.gameController != null)
            this.gameController.reset();
        switch (gameName) {
            case "Tetris":
                this.gameController = new TetrisController(this.board);
                this.board.setGame(gameController.getGame());
                this.boardView.setBody(gameController.getGameView());
                break;
            case "Blokus":
                this.gameController = new BlokusController(this.board);
                this.board.setGame(gameController.getGame());
                this.boardView.setBody(gameController.getGameView());
                break;
            default:
                this.boardView.setBody(new DefaultView("Not implemented yet !"));
                break;
        }
    }

    public BoardView getBoardView() {
        return this.boardView;
    }
}
