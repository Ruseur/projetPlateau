package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class GameView extends Pane implements EventHandler<Event> {

    public Pane render() {
        return new DefaultPanel();
    }

    @Override
    public void handle(Event event) {

    }
}
