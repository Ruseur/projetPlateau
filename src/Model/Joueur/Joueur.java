package Model.Joueur;

import javafx.scene.paint.Color;

import java.util.Observable;

public class Joueur extends Observable {
    private String nom;
    private int score = 0;
    private String status = "playing";
    private Color color;

    public Joueur(String nom){
        this.setNom(nom);
    }

    public Joueur(String nom, Color color){
        this.setNom(nom);
        this.setColor(color);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setChanged();
        notifyObservers("ScoreUpdate");
    }
}
