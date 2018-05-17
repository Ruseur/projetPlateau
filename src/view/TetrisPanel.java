/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.TetrisController;
import Model.Plateau.Case;
import Model.Plateau.Grille;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vvhuan
 */
public class TetrisPanel extends GamePanel implements Observer{


    public TetrisPanel(){
        this.add(new Label("Tetris yolo Panel !"), 2, 0);
        //Button playButton = new Button("Play");
        //this.add(playButton, 2, 0);
    }

    public TetrisPanel(Grille grille){
        Button btn = new Button("Start");
        /*btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });*/
        this.add(btn,40,3);
        for (int i=0; i<grille.getLargeur(); i++)
            for (int j=0; j<grille.getHauteur(); j++){
                Rectangle rec = new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setFill(grille.getCase(j,i).getColor());
                rec.setStrokeWidth(0.1);
                rec.setStroke(Color.WHITE);
                this.add(rec, i, j);
            }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Grille && arg instanceof Case)
        {
            for(Node n: getChildren()){
                if(GridPane.getRowIndex(n) == ((Case)arg).getY() && GridPane.getColumnIndex(n) == ((Case)arg).getX())
                    ((Rectangle) n).setFill(((Case)arg).getColor());
            }
        }
    }
}
