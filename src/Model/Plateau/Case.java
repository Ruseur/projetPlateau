package Model.Plateau;

import javafx.scene.paint.Color;

public class Case {
    int y;
    int x;
    Piece piece;
    private Color color;

    public Case(int y, int x) {
        this.piece = null;
        this.y = y;
        this.x = x;
        this.color = Color.WHITE;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void raz(){
        this.piece = null;
    }

    public boolean setPiece(Piece piece) {
        if(this.piece == null || piece.getId() == this.getPiece().getId()){
            this.piece = piece;
            return true;
        }else{
            return false;
        }
    }

    public Piece getPiece() {
        return piece;
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
