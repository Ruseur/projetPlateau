package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import view.GameView;

public class BoardController implements EventHandler<ActionEvent> {
    private BorderPane borderpane;

    public BoardController(BorderPane borderpane) {
        this.borderpane = borderpane;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof MenuItem){
            String source = ((MenuItem)event.getSource()).getText();
            switch (source) {
                case "Quit":
                    Platform.exit();
                    break;
                case "Tetris":
                    TetrisController tetrisController = new TetrisController();
                    this.loadView(tetrisController.getView());
                    break;
                default:
                    this.loadView(new GameView());
                    break;
            }
        }
    }

    private void loadView(GameView view) {
        this.borderpane.setCenter(view.render());
    }
}
