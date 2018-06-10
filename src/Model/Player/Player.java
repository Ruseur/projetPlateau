package Model.Player;

import javafx.scene.paint.Color;

import java.util.Observable;

public class Player extends Observable {
    private String name;
    private int score = 0;
    private String status = "playing";
    private Color color;

    public Player(String name){
        this.setName(name);
    }

    public Player(String name, Color color){
        this.setName(name);
        this.setColor(color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        setChanged();
        notifyObservers("ScoreUpdate");
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
