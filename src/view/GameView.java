package view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import java.util.Observer;

public abstract class GameView extends BorderPane implements EventHandler<Event>, Observer {

    protected abstract void loadHomeView();
    protected abstract void loadInGameView();
    protected abstract void loadFinishView();
    void cleanView() {
        this.getChildren().removeAll(this.getChildren());
    }

}
