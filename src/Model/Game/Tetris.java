package Model.Game;

import Model.Board.Grid;
import Model.Player.Player;

public class Tetris extends Game {

    private Player player;
    private Grid nextPieceGrid;
    private int level;

    public Tetris(){}

    public Tetris(Grid grid) {
        this.setGrid(grid);
    }

    public Grid getNextPieceGrid() {
        return nextPieceGrid;
    }

    public void setNextPieceGrid(Grid nextPieceGrid) {
        this.nextPieceGrid = nextPieceGrid;
        setChanged();
        notifyObservers(nextPieceGrid);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        setChanged();
        notifyObservers("PlayerUpdate");
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setChanged();
        notifyObservers("LevelUpdate");
    }
}
