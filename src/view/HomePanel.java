/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.Controleur;
import Model.Plateau.Case;
import Model.Plateau.Grille;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vvhuan
 */
public class homePanel extends GridPane{

    public homePanel(){
        this.add(new Label("Hello world !"), 2, 0);
    }

}
