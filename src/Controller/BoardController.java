package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import view.GamePanel;
import view.TetrisPanel;

import java.awt.*;

public class BoardController implements EventHandler<ActionEvent> {
    BorderPane borderpane;

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
                    this.loadPanel(tetrisController.getGamePanel());
                    break;
                default:
                    this.loadPanel(new GamePanel());
                    break;
            }



        }
    }

    public void loadPanel(GamePanel panel) {
        this.borderpane.setCenter(panel);
    }
}
