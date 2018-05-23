package view;

import Controller.TetrisController;
import Model.Jeu.Tetris;
import Model.Joueur.Joueur;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Observable;

public class TetrisView extends GameView implements EventHandler<Event> {

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

        VBox body = new VBox();
        body.getChildren().addAll(this.getPlayerView(), this.getBestScoresView());

        this.setCenter(body);
        this.setBottom(this.getGameControllerView());
    }


    @Override
    public void loadInGameView() {
        this.cleanView();
        this.setTop(this.getTopPane());

        GrilleView grilleView = new GrilleView(this.jeu.getGrille());
        grilleView.setAlignment(Pos.CENTER);
        this.setCenter(grilleView);

        BorderPane rightPane = new BorderPane();
        rightPane.setTop(this.getInfoView());
        rightPane.setCenter(this.getGameControllerView());
        rightPane.setBottom(this.getInGameControllerView());
        this.setRight(rightPane);
    }

    @Override
    protected void loadFinishView() {
        this.cleanView();
        this.setTop(this.getTopPane());
        this.setCenter(this.getBestScoresView());
        this.setBottom(this.getGameControllerView());
    }

    private Pane getPlayerView() {
        VBox playerView = new VBox();
        playerView.setPadding(new Insets(10));
        playerView.setSpacing(8);
        playerView.setAlignment(Pos.CENTER);

        if(this.jeu.getJoueur() != null) {
            Text title = new Text("Hello "+ this.jeu.getJoueur().getNom()+" !");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            playerView.getChildren().add(title);
        } else {
            Text title = new Text("Who are you ?");
            title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            playerView.getChildren().add(title);

            HBox playerActionBox = new HBox();
            playerActionBox.setAlignment(Pos.CENTER);
            TextField playerNameField = new TextField();
            playerNameField.setId("PlayerNameField");
            Button confirmButton = new Button("Confirm");
            confirmButton.setId("PlayerNameConfirm");
            confirmButton.setOnMouseClicked(this);

            playerActionBox.getChildren().addAll(playerNameField,confirmButton);
            playerView.getChildren().add(playerActionBox);
        }

        return playerView;
    }

    private Pane getBestScoresView() {
        VBox bestScoreView = new VBox();
        bestScoreView.setId("BestScoreView");
        bestScoreView.setAlignment(Pos.CENTER);

        bestScoreView.setPadding(new Insets(10));
        bestScoreView.setSpacing(8);

        Text title = new Text("Best Scores");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bestScoreView.getChildren().add(title);

        ArrayList<String> bestScores = this.tetrisController.getSavedScores();
        if(bestScores.size() == 0) {
            Text text = new Text("No scores yet ! :(");
            bestScoreView.getChildren().add(text);
        } else {
            for (String bestScore : bestScores) {
                String[] splittedScore = bestScore.split(":");
                bestScoreView.getChildren().add(new Text(splittedScore[0] + " - " + splittedScore[1]));
            }
        }

        return bestScoreView;
    }

    private Pane getGameControllerView() {
        VBox gameControllerView = new VBox();
        gameControllerView.setId("GameControllerView");
        gameControllerView.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play");
        playButton.setId("PlayButton");
        playButton.setMaxWidth(Double.MAX_VALUE);
        playButton.setOnMouseClicked(this);
        if(this.jeu.getJoueur() == null) playButton.setDisable(true);

        Button resumeButton = new Button("Resume");
        resumeButton.setId("ResumeButton");
        resumeButton.setMaxWidth(Double.MAX_VALUE);
        resumeButton.setOnMouseClicked(this);

        Button pauseButton = new Button("Pause");
        pauseButton.setId("PauseButton");
        pauseButton.setMaxWidth(Double.MAX_VALUE);
        pauseButton.setOnMouseClicked(this);

        Button resetButton = new Button("Reset");
        resetButton.setId("ResetButton");
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnMouseClicked(this);

        String gameStatus = this.jeu.getStatus();
        switch (gameStatus) {
            case "playing":
                gameControllerView.getChildren().addAll(pauseButton,resetButton);
                break;
            case "paused":
                gameControllerView.getChildren().addAll(resumeButton,resetButton);
                break;
            case "finished":
                playButton.setText("Play again");
            case "initial":
                gameControllerView.getChildren().add(playButton);
                break;

        }
        return gameControllerView;
    }

    private Pane getInGameControllerView() {

        GridPane inGameControllerView = new GridPane();

        Button left = new Button("Left");
        left.setId("LeftButton");
        left.setOnMouseClicked(this);
        left.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(left,0,1);

        Button right = new Button("Right");
        right.setId("RightButton");
        right.setOnMouseClicked(this);
        right.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(right,2,1);

        Button up = new Button("Up");
        up.setId("UpButton");
        up.setOnMouseClicked(this);
        up.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(up,1,0);

        Button down = new Button("Down");
        down.setId("DownButton");
        down.setOnMouseClicked(this);
        down.setMaxWidth(Double.MAX_VALUE);
        inGameControllerView.add(down,1,1);

        inGameControllerView.setAlignment(Pos.CENTER);

        return inGameControllerView;
    }

    private Pane getScoreView() {
        this.jeu.getJoueur().addObserver(this);

        GridPane scoreView = new GridPane();
        scoreView.setId("ScoreView");
        Text scoreValue = new Text(Integer.toString(this.jeu.getJoueur().getScore()));
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

        if(this.jeu.getStatus().equals("playing") || this.jeu.getStatus().equals("paused")) {
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
            if(arg.equals("LevelUpdate") && this.jeu.getStatus().equals("playing")) {
                ((Text)this.lookup("#LevelValue")).setText(Integer.toString(this.jeu.getLevel()));
            }
            if(arg.equals("PlayerUpdate")) {
                this.loadHomeView();
            }

            if(arg.equals("StatusUpdate")) {
                switch (this.jeu.getStatus()) {
                    case "playing":
                        this.loadInGameView();
                        break;
                    case "paused":
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

        if(o instanceof Joueur) {
            if(arg.equals("ScoreUpdate") && this.jeu.getStatus().equals("playing")) {
                ((Text) this.lookup("#ScoreValue")).setText(Integer.toString(this.jeu.getJoueur().getScore()));
            }
        }
    }

    @Override
    public void handle(Event event) {
        String action = "";
        if (event.getSource() instanceof Button) {
            action = ((Button) event.getSource()).getText();
            if(action.equals("Play again")) action = "Play";
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
            case "Resume":
                this.tetrisController.command("Resume");
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
            case "Confirm":
                this.tetrisController.setJoueur(((TextField)this.lookup("#PlayerNameField")).getText());
                break;
            default:
                break;
        }
    }


}
