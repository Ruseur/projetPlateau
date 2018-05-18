/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import view.GameView;

/**
 *
 * @author vvhuan
 */
public abstract class GameController{

    protected GameView gameView;

    public GameController(){
        GameView gamePanel = new GameView();
        this.gameView = gamePanel;
    }

    public GameView getView() {
        return gameView;
    }
}
