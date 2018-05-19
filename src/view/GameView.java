package view;

import Controller.GameController;
import Model.Jeu.Jeu;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class GameView extends BorderPane implements EventHandler<Event>, Observer {

    private GameController gameController;
    private Jeu jeu;

    public GameView(GameController gameController, Jeu jeu) {
        this.gameController = gameController;

        this.jeu = jeu;
        jeu.addObserver(this);
    }

    @Override
    public void handle(Event event) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Jeu && arg.equals("ScoreUpdate")) {
            ScoreView scoreView = (ScoreView) this.lookup("ScoreView");
            scoreView.update(this.jeu.getScore());
        }
    }

    public Jeu getJeu() {
        return jeu;
    }

    protected void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public GameController getGameController() {
        return gameController;
    }
}
