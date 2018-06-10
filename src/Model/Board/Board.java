package Model.Board;

import Model.Game.Game;

import java.util.Observable;

public class Board extends Observable {
    private Game game;

    public Board() {

    }

    public Board(Game game) {
        this.game = game;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        setChanged();
        notifyObservers(game);
    }
}
