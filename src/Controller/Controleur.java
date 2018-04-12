/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Plateau.Grille;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import view.Main;

/**
 *
 * @author vvhuan
 */
public class Controleur implements EventHandler<MouseEvent>{
    private Main vue;
    private Grille grille;

    public Controleur(Main vue, Grille grille){
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
    
}
