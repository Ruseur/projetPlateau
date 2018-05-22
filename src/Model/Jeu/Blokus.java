package Model.Jeu;

import Model.Joueur.Joueur;
import Model.Plateau.Grille;
import Model.Plateau.Piece;

import java.util.ArrayList;

public class Blokus extends Jeu {

    private ArrayList<Joueur> players;
    private ArrayList<Piece> pieces;
    private Joueur currentPlayer;
    private Grille currentGridPlayer;

    public Blokus(Grille grille) {
        this.setPlayers(new ArrayList<Joueur>());
        this.setGrille(grille);
    }

    public Joueur getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Joueur currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Joueur> getPlayers() {
        return players;
    }

    public void addJoueur(Joueur player) {
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
    }
}
