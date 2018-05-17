package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class GameView extends Pane implements EventHandler<ActionEvent> {

    public Pane render() {
        return new DefaultPanel();
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
