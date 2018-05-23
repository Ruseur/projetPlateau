/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Model.Player.Player;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

public class PlayerView extends HBox implements Observer{


    public PlayerView(Player player){
        Text text = new Text(player.getName() + " - " + Integer.toString(player.getScore()));
        text.setId("Player"+ player.getName());

        if(player.getColor() != null)
            text.setFill(player.getColor());

        this.getChildren().add(text);
        player.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Player && arg instanceof String) {

        }
    }
}
