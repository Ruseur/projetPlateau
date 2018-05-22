package Model.Jeu;

import Model.Joueur.Joueur;
import Model.Plateau.Grille;

import java.util.ArrayList;

public class Blokus extends Jeu {

    private ArrayList<Joueur> players;
    private Joueur currentPlayer;

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
}
