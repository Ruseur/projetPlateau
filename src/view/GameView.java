package view;

import javafx.scene.layout.Pane;

public class GameView extends Pane{

    public Pane render() {
        return new DefaultPanel();
    }
}
