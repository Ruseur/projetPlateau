package view;

import Controller.TetrisController;
import Model.Plateau.Grille;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TetrisView extends GameView{


    private TetrisController tetrisController;
    private GrillePanel grillePanel;

    public TetrisView(TetrisController tetrisController, Grille grille) {

        GrillePanel grillePanel = new GrillePanel(grille);
        grille.addObserver(grillePanel);

        this.grillePanel = grillePanel;
        this.tetrisController = tetrisController;
    }

    private Pane getRightPane() {
        GridPane rightPane = new GridPane();


        Button play = new Button("Play");
        play.setOnMouseClicked(this);
        rightPane.add(play,0,0);

        Button pause = new Button("Pause");
        pause.setOnMouseClicked(this);
        rightPane.add(pause,1,0);

        Button reset = new Button("Reset");
        reset.setOnMouseClicked(this);
        rightPane.add(reset,0,1);

        Button left = new Button("Left");
        left.setOnMouseClicked(this);
        rightPane.add(left,0,2);

        Button right = new Button("Right");
        right.setOnMouseClicked(this);
        rightPane.add(right,1,2);

        Button up = new Button("Up");
        up.setOnMouseClicked(this);
        rightPane.add(up,0,3);

        Button down = new Button("Down");
        down.setOnMouseClicked(this);
        rightPane.add(down,1,3);

        rightPane.setOnKeyPressed(this);


        return rightPane;
    }


    @Override
    public void handle(Event event) {
        String action = "";
        if (event.getSource() instanceof Button) {
            action = ((Button) event.getSource()).getText();
        }
        if (event instanceof KeyEvent) {
            switch (((KeyEvent) event).getCode()) {
                case UP: action = "Up"; break;
                case DOWN: action = "Down"; break;
                case RIGHT: action = "Right"; break;
                case LEFT: action = "Left"; break;
            }
        }
        switch (action) {
            case "Play":
                this.tetrisController.command("Play");
                break;
            case "Pause":
                this.tetrisController.command("Pause");
                break;
            case "Reset":
                this.tetrisController.command("Reset");
                break;
            case "Left":
                this.tetrisController.command("Left");
                break;
            case "Right":
                this.tetrisController.command("Right");
                break;
            case "Up":
                this.tetrisController.command("Up");
                break;
            case "Down":
                this.tetrisController.command("Down");
                break;
            default:
                break;
        }
    }

    @Override
    public Pane render() {

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.grillePanel);

        borderPane.setRight(this.getRightPane());

        return borderPane;
    }

}
