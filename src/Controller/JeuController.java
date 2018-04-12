package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class JeuController  implements EventHandler<ActionEvent> {
   
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof MenuItem){
            if (((MenuItem)event.getSource()).getText().equals("Quit"))
                Platform.exit();
        }
    }
}
