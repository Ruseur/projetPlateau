/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.TetrisController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

    @Override
    public void update(Observable o, Object arg) {

    }
}
