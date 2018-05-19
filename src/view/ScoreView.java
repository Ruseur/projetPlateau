/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Plateau.Case;
import Model.Plateau.Grille;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vvhuan
 */
public class ScoreView extends GridPane{


    public ScoreView(int score){
        this.setId("ScoreView");
        this.add(new Text("Score: " + Integer.toString(score)),0,0);
    }

    public void update(int score) {
        this.getChildren().set(0,new Text("Score: " + Integer.toString(score)));

    }
}
