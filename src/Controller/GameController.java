/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Jeu.Jeu;
import Model.Plateau.Plateau;
import view.GameView;

/**
 *
 * @author vvhuan
 */
public abstract class GameController{
    protected Plateau plateau;
    GameView gameView;
    Jeu jeu;

    GameController(Plateau plateau) {
        this.plateau = plateau;
    }

    GameView getGameView() {
        return this.gameView;
    }

    Jeu getJeu(){return jeu;}

    public abstract void reset();
}
