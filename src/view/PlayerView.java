/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Jeu.Jeu;
import Model.Joueur.Joueur;
import Model.Plateau.Case;
import Model.Plateau.Grille;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vvhuan
 */
public class PlayerView extends HBox implements Observer{


    public PlayerView(Joueur joueur){
        Text text = new Text(joueur.getNom() + " - " + Integer.toString(joueur.getScore()));
        text.setId("Player"+joueur.getNom());
        text.setFill(joueur.getColor());
        this.getChildren().add(text);
        joueur.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Joueur && arg instanceof String) {

        }
    }
}
