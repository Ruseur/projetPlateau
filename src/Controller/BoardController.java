package Controller;

import Model.Plateau.Plateau;
import view.BoardView;
import view.DefaultView;

public class BoardController {
    private BoardView boardView;
    private GameController gameController;
    private Plateau plateau;

    public BoardController() {
        this.plateau = new Plateau();
        this.boardView = new BoardView(this, this.plateau);
    }

    public void switchGame(String gameName){
        if(this.gameController != null)
            this.gameController.reset();
        switch (gameName) {
            case "Tetris":
                this.gameController = new TetrisController(this.plateau);
                this.plateau.setJeu(gameController.getJeu());
                this.boardView.setBody(gameController.getGameView());
                break;
            case "Blokus":
                this.gameController = new BlokusController(this.plateau);
                this.plateau.setJeu(gameController.getJeu());
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
