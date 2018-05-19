package view;

import Controller.TetrisController;
import Model.Jeu.Tetris;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TetrisView extends GameView{

    private Tetris jeu;
    private TetrisController tetrisController;


    public TetrisView(TetrisController tetrisController, Tetris tetris) {
        super(tetrisController, tetris);

        this.jeu = tetris;
        this.tetrisController = tetrisController;

        this.loadHomeView();

        this.setOnKeyPressed(this);
    }

    public void loadHomeView() {
        this.setTop(this.getTopPane());
        this.setCenter(this.getRightPane());
    }


    @Override
    public void loadInGameView() {
        this.setTop(this.getTopPane());
        this.setCenter(new GrilleView(this.getJeu().getGrille()));
        this.setRight(this.getRightPane());
    }

    @Override
    protected void loadFinishView() {
        this.setCenter(new DefaultView("fini"));
        this.setRight(this.getRightPane());
    }


    private Pane getRightPane() {

        BorderPane containerPane = new BorderPane();


        containerPane.setTop(this.getInfoBox());

        FlowPane gameControllerPane = new FlowPane();

        Button play = new Button("Play");
        play.setOnMouseClicked(this);

        Button pause = new Button("Pause");
        pause.setOnMouseClicked(this);

        Button reset = new Button("Reset");
        reset.setOnMouseClicked(this);

        gameControllerPane.getChildren().addAll(play,pause,reset);
        containerPane.setCenter(gameControllerPane);


        GridPane tetrisControllerPane = new GridPane();
        Button left = new Button("Left");
        left.setOnMouseClicked(this);
        tetrisControllerPane.add(left,0,1);

        Button right = new Button("Right");
        right.setOnMouseClicked(this);
        tetrisControllerPane.add(right,2,1);

        Button up = new Button("Up");
        up.setOnMouseClicked(this);
        tetrisControllerPane.add(up,1,0);

        Button down = new Button("Down");
        down.setOnMouseClicked(this);
        tetrisControllerPane.add(down,1,1);


        containerPane.setBottom(tetrisControllerPane);

        return containerPane;
    }
    private VBox getInfoBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Info");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        vbox.getChildren().add(title);

        ArrayList<Pane> options = new ArrayList<Pane>();
        options.add(new ScoreView(this.getJeu().getScore()));

        if(this.jeu.getStatus().equals("playing")) {
            options.add(new GrilleView(this.jeu.getNextPieceGrille()));
        }

        for (Pane option : options) {
            VBox.setMargin(option, new Insets(0, 0, 0, 8));
            vbox.getChildren().add(option);
        }

        return vbox;
    }

    private Pane getTopPane() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #336699;");
        Label title = new Label("Tetris");
        title.setStyle("\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: #333333;");
        hbox.getChildren().add(title);

        return hbox;
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


}
