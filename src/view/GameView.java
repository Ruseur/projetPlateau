package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class GameView extends Pane implements EventHandler<Event>, Observer {

    public Pane render() {
        return new DefaultPanel();
    }

    @Override
    public void handle(Event event) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
