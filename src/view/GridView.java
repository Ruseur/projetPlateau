/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Board.Case;
import Model.Board.Grid;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;

public class GridView extends GridPane implements Observer{


    public GridView(Grid grid){
        this.generateGrid(grid);
        grid.addObserver(this);
    }

    public void generateGrid(Grid grid) {
        for (int i = 0; i< grid.getWidth(); i++)
            for (int j = 0; j< grid.getHeight(); j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setFill(grid.getCase(j,i).getColor());
                rec.setStrokeWidth(0.1);
                rec.setStroke(Color.WHITE);
                this.add(rec, i, j);
            }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Grid && arg instanceof Case) {
            for (Node n : getChildren()) {
                if (GridPane.getRowIndex(n) == ((Case) arg).getY() && GridPane.getColumnIndex(n) == ((Case) arg).getX())
                    ((Rectangle) n).setFill(((Case) arg).getColor());
            }
        }
    }
}
