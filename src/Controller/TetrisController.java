package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import view.TetrisPanel;

public class TetrisController extends GameController  implements EventHandler<MouseEvent> {


    public TetrisController(){
        TetrisPanel tetrisPanel = new TetrisPanel();
        super.gamePanel = tetrisPanel;
    }


    public void start() {


    }




}
