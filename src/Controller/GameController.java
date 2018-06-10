/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board.Board;
import Model.Game.Game;
import view.GameView;

public abstract class GameController{
    protected Board board;
    GameView gameView;
    private Game game;

    GameController(Board board) {
        this.board = board;
    }

    GameView getGameView() {
        return this.gameView;
    }

    Game getGame(){return game;}

    public abstract void reset();
}
