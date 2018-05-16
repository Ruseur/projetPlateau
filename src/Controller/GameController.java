/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Plateau.Grille;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import view.GamePanel;
import view.Main;

/**
 *
 * @author vvhuan
 */
public class GameController implements EventHandler<MouseEvent>{

    protected GamePanel gamePanel;
    private BoardController boardController;
    private Main vue;
    private Grille grille;

    public GameController(){
        GamePanel gamePanel = new GamePanel();
        this.gamePanel = gamePanel;
    }

    public GameController(Main vue, Grille grille){
        this.vue = vue;
        this.grille = grille;
    }
    
    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
            if (event.getSource() instanceof Rectangle)
            {
                Rectangle r = (Rectangle) event.getSource();
                grille.entered(GridPane.getRowIndex(r), GridPane.getColumnIndex(r));
            }
        } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
            if (event.getSource() instanceof Rectangle)
            {
                Rectangle r = (Rectangle) event.getSource();
                grille.exited(GridPane.getRowIndex(r), GridPane.getColumnIndex(r));
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
