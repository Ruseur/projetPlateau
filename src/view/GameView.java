package view;

import Controller.GameController;
import Model.Jeu.Jeu;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
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
        System.out.println("gameView");

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

    protected void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
