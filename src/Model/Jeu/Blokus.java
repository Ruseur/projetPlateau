package Model.Jeu;

import Model.Joueur.Joueur;
import Model.Plateau.Grille;
import Model.Plateau.Piece;

import java.util.ArrayList;

public class Blokus extends Jeu {

    private ArrayList<Joueur> players;
    private ArrayList<Piece> pieces;
    private ArrayList<ArrayList<Piece>> playersPieces;
    private Joueur currentPlayer;
    private ArrayList<Piece> currentPlayerPieces;
    private Grille currentGridPlayer;

    public Blokus(Grille grille) {
        this.setPlayers(new ArrayList<Joueur>());
        this.setGrille(grille);
    }

    public Joueur getCurrentPlayer() {
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

    public Joueur getNextPlayer() {
        Integer nextIndex = this.getNextPlayerIndex();
        return this.players.get(nextIndex);
    }

    public void setCurrentPlayer(Joueur currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Joueur> getPlayers() {
        return players;
    }

    public void addPlayer(Joueur player) {
        this.players.add(player);
    }

    public void setPlayers(ArrayList<Joueur> players) {
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

    public Grille getCurrentGridPlayer() {
        return currentGridPlayer;
    }

    public void setCurrentGridPlayer(Grille currentGridPlayer) {
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
