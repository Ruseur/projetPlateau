/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Plateau.Grille;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import view.GameView;
import view.Main;

/**
 *
 * @author vvhuan
 */
public abstract class GameController implements EventHandler<ActionEvent>{

    protected GameView gameView;

    public GameController(){
        GameView gamePanel = new GameView();
        this.gameView = gamePanel;
    }

    public GameView getView() {
        return gameView;
    }
}
