package view;

import Controller.TetrisController;
import Model.Plateau.Grille;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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


    @Override
    public Pane render() {

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.grillePanel);

        Button play = new Button("Play");
        play.setOnAction(this.tetrisController);

        borderPane.setRight(play);

        return borderPane;
    }
}
