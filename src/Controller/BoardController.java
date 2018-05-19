package Controller;

import Model.Plateau.Plateau;
import javafx.scene.layout.Pane;
import view.BoardView;
import view.GameView;

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
                this.boardView.setGameView(tetrisController.getGameView());
                break;
        }
    }

    public BoardView getBoardView() {
        return this.boardView;
    }
}
