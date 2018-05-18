/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Plateau.Plateau;
import view.GameView;

/**
 *
 * @author vvhuan
 */
public abstract class GameController{
    protected Plateau plateau;
    protected GameView gameView;

    public GameController(Plateau plateau) {
        this.plateau = plateau;
    }

    public GameView getGameView() {
        return this.gameView;
    }
}
