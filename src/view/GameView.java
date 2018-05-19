package view;

import Controller.GameController;
import Model.Jeu.Jeu;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public abstract class GameView extends BorderPane implements EventHandler<Event>, Observer {

    private GameController gameController;
    private Jeu jeu;

    public GameView(GameController gameController, Jeu jeu) {
        this.gameController = gameController;

        this.jeu = jeu;
        jeu.addObserver(this);
    }

    protected abstract void loadHomeView();
    protected abstract void loadInGameView();
    protected abstract void loadFinishView();

    @Override
    public void handle(Event event) {

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Jeu && arg.equals("ScoreUpdate")) {
            ScoreView scoreView = (ScoreView) this.lookup("ScoreView");
            scoreView.update(this.jeu.getScore());
        }

        if(o instanceof Jeu && arg.equals("StatusUpdate")) {
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



    public Jeu getJeu() {
        return jeu;
    }


}
