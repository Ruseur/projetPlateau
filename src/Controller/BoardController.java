package Controller;

import Model.Plateau.Plateau;
import view.BoardView;
import view.DefaultView;

public class BoardController {
    private BoardView boardView;
    private Plateau plateau;

    public BoardController() {
        this.plateau = new Plateau();
        this.boardView = new BoardView(this, this.plateau);
    }

    public void changeGame(String gameName){
        switch (gameName) {
            case "Tetris":
                TetrisController tetrisController = new TetrisController(this.plateau);
                this.boardView.setBody(tetrisController.getGameView());
                break;
            case "Blokus":
                BlokusController blokusController = new BlokusController(this.plateau);
                this.boardView.setBody(blokusController.getGameView());
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
