package Model.Game;

import Model.Board.Grid;
import Model.Player.Player;
import Model.Board.Piece;

import java.util.ArrayList;

public class Blokus extends Game {

    private ArrayList<Player> players;
    private ArrayList<Piece> pieces;
    private ArrayList<ArrayList<Piece>> playersPieces;
    private Player currentPlayer;
    private ArrayList<Piece> currentPlayerPieces;
    private Grid currentGridPlayer;

    public Blokus(Grid grid) {
        this.setPlayers(new ArrayList<Player>());
        this.setGrid(grid);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Integer getNextPlayerIndex() {
        int nextIndex;
        int currentIndex = this.players.indexOf(this.currentPlayer);
        if(currentIndex == this.players.size() - 1) {
            nextIndex = 0;
        } else {
            nextIndex = currentIndex + 1;
        }
        return nextIndex;
    }

    public Player getNextPlayer() {
        Integer nextIndex = this.getNextPlayerIndex();
        return this.players.get(nextIndex);
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public Grid getCurrentGridPlayer() {
        return currentGridPlayer;
    }

    public void setCurrentGridPlayer(Grid currentGridPlayer) {
        this.currentGridPlayer = currentGridPlayer;
        setChanged();
        notifyObservers("PlayerGridUpdate");
    }

    public ArrayList<Piece> getCurrentPlayerPieces() {
        return currentPlayerPieces;
    }

    public void setCurrentPlayerPieces(ArrayList<Piece> currentPlayerPieces) {
        this.currentPlayerPieces = currentPlayerPieces;
        setChanged();
        notifyObservers("PlayerPiecesUpdate");
    }

    public ArrayList<ArrayList<Piece>> getPlayersPieces() {
        return playersPieces;
    }

    public void setPlayersPieces(ArrayList<ArrayList<Piece>> playersPieces) {
        this.playersPieces = playersPieces;
    }
}
