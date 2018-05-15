/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.GameController;
import Model.Plateau.Case;
import Model.Plateau.Grille;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author vvhuan
 */
public class GridPanel extends GridPane implements Observer{
    
    public GridPanel(Grille grille, GameController gameControleur){
        for (int i=0; i<grille.getLargeur(); i++)
           for (int j=0; j<grille.getHauteur(); j++){
               Rectangle rec = new Rectangle();
               rec.setWidth(100);
               rec.setHeight(100);
               rec.setFill(grille.getCase(i,j).getColor());
               rec.setStroke(Color.BLACK);
               rec.setOnMouseEntered(gameControleur);
               rec.setOnMouseExited(gameControleur);
               this.add(rec, i, j);
            }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Grille && arg instanceof Case)
        {
            for(Node n: getChildren()){
                if(GridPane.getRowIndex(n) == ((Case)arg).getX() && GridPane.getColumnIndex(n) == ((Case)arg).getY())
                ((Rectangle) n).setFill(((Case)arg).getColor());
            }
        }
    }
}
