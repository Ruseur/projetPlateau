package view;

import Controller.TetrisController;
import Model.Plateau.Grille;
import javafx.scene.control.Button;
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
        play.setOnAction(this.tetrisController);
        rightPane.add(play,0,0);

        Button pause = new Button("Pause");
        pause.setOnAction(this.tetrisController);
        rightPane.add(pause,1,0);

        Button reset = new Button("Reset");
        reset.setOnAction(this.tetrisController);
        rightPane.add(reset,0,1);

        Button left = new Button("Left");
        left.setOnAction(this.tetrisController);
        rightPane.add(left,0,2);

        Button right = new Button("Right");
        right.setOnAction(this.tetrisController);
        rightPane.add(right,1,2);

        Button up = new Button("Up");
        up.setOnAction(this.tetrisController);
        rightPane.add(up,0,3);

        Button down = new Button("Down");
        down.setOnAction(this.tetrisController);
        rightPane.add(down,1,3);

        return rightPane;
    }

    @Override
    public Pane render() {

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.grillePanel);

        borderPane.setRight(this.getRightPane());

        return borderPane;
    }
}
