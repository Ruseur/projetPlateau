package view;

import Controller.TetrisController;
import Model.Jeu.Tetris;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Observable;

public class TetrisView extends GameView{

    private Tetris jeu;
    private TetrisController tetrisController;


    public TetrisView(TetrisController tetrisController, Tetris tetris) {
        this.jeu = tetris;
        tetris.addObserver(this);

        this.tetrisController = tetrisController;

        this.setId("TetrisView");
        this.loadHomeView();

        this.setOnKeyPressed(this);
    }

    public void loadHomeView() {
        this.cleanView();
        this.setTop(this.getTopPane());
        this.setCenter(this.getGameControllerView());
    }


    @Override
    public void loadInGameView() {
        this.cleanView();
        this.setTop(this.getTopPane());
        this.setCenter(new GrilleView(this.jeu.getGrille()));
        this.setRight(this.getRightPane());
    }

    @Override
    protected void loadFinishView() {
        this.cleanView();
        this.setTop(this.getTopPane());
        this.setCenter(this.getGameControllerView());
    }


    private Pane getRightPane() {

        BorderPane containerPane = new BorderPane();


        containerPane.setTop(this.getInfoView());

        containerPane.setCenter(this.getGameControllerView());

        containerPane.setBottom(this.getInGameControllerView());

        return containerPane;
    }

    private Pane getGameControllerView() {
        VBox gameControllerView = new VBox();
        gameControllerView.setId("GameControllerView");
        gameControllerView.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play");
        playButton.setMaxWidth(Double.MAX_VALUE);
        playButton.setOnMouseClicked(this);

        Button pauseButton = new Button("Pause");
        pauseButton.setMaxWidth(Double.MAX_VALUE);
        pauseButton.setOnMouseClicked(this);

        Button resetButton = new Button("Reset");
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnMouseClicked(this);

        String gameStatus = this.jeu.getStatus();
        switch (gameStatus) {
            case "playing":
                playButton.setDisable(true);
                break;
            case "paused":
                pauseButton.setDisable(true);
                break;
            case "ended":
                pauseButton.setDisable(true);
                resetButton.setDisable(true);
                break;
            case "initial":
                pauseButton.setDisable(true);
                resetButton.setDisable(true);
                break;

        }


        gameControllerView.getChildren().addAll(playButton,pauseButton,resetButton);
        return gameControllerView;
    }

    private Pane getInGameControllerView() {

        GridPane inGameControllerView = new GridPane();
        Button left = new Button("Left");
        left.setOnMouseClicked(this);
        left.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(left,0,1);

        Button right = new Button("Right");
        right.setOnMouseClicked(this);
        right.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(right,2,1);

        Button up = new Button("Up");
        up.setOnMouseClicked(this);
        up.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(up,1,0);

        Button down = new Button("Down");
        down.setOnMouseClicked(this);
        down.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(down,1,1);
        inGameControllerView.setAlignment(Pos.CENTER);

        return inGameControllerView;
    }

    private Pane getScoreView() {
        GridPane scoreView = new GridPane();
        scoreView.setId("ScoreView");
        Text scoreValue = new Text(Integer.toString(this.jeu.getScore()));
        scoreValue.setId("ScoreValue");
        scoreView.add(new Text("Score: "),0,0);
        scoreView.add(scoreValue,1,0);
        return scoreView;
    }

    private Pane getLevelView() {
        GridPane levelView = new GridPane();
        levelView.setId("LevelView");
        Text levelValue = new Text(Integer.toString(this.jeu.getLevel()));
        levelValue.setId("LevelValue");
        levelView.add(new Text("Niveau: "),0,0);
        levelView.add(levelValue,1,0);
        return levelView;
    }


    private VBox getInfoView() {
        VBox vbox = new VBox();
        vbox.setId("InfoView");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Info");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        vbox.getChildren().add(title);

        ArrayList<Pane> options = new ArrayList<Pane>();

        if(this.jeu.getStatus().equals("playing")) {
            options.add(this.getScoreView());
            options.add(new GrilleView(this.jeu.getNextPieceGrille()));
            options.add(this.getLevelView());
        }

        for (Pane option : options) {
            VBox.setMargin(option, new Insets(0, 0, 0, 8));
            vbox.getChildren().add(option);
        }

        return vbox;
    }

    private Pane getTopPane() {

        HBox hbox = new HBox();

        BackgroundFill backgroundFill = new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(90),new Insets(2));
        hbox.setBackground(new Background(backgroundFill));
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        Text title = new Text("Tetris");
        title.setTextAlignment(TextAlignment.CENTER);
        hbox.getChildren().add(title);

        return hbox;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Tetris) {
            if(arg.equals("ScoreUpdate")) {
                ((Text) this.lookup("#ScoreValue")).setText(Integer.toString(this.jeu.getScore()));
            }
            if(arg.equals("LevelUpdate")) {
                ((Text)this.lookup("#LevelValue")).setText(Integer.toString(this.jeu.getLevel()));
            }

            if(arg.equals("StatusUpdate")) {
                switch (this.jeu.getStatus()) {
                    case "playing":
                        this.loadInGameView();
                        break;
                    case "finished":
                        this.loadFinishView();
                        break;
                    default:
                        this.loadHomeView();
                        break;
                }
            }
        }
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
