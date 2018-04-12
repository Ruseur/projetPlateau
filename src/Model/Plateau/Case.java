package Model.Plateau;

import javafx.scene.paint.Color;

public class Case {
    int y;
    int x;
    private Color color;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
