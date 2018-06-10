package Model.Game;

import Model.Board.Grid;

import java.util.Observable;

public class Game extends Observable {
    // "playing" "paused" "initial" "finished"
    private String status = "initial";
    private Grid grid;

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setChanged();
        notifyObservers("StatusUpdate");
    }
}
