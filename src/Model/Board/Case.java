package Model.Board;

import javafx.scene.paint.Color;

public class Case {
    int y;
    int x;
    Piece piece;
    private Color color;

    Case(int y, int x) {
        this.piece = null;
        this.y = y;
        this.x = x;
        this.color = Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    void raz(){
        this.piece = null;
        this.setColor(Color.BLACK);
    }

    boolean setPiece(Piece piece) {
        if(this.piece == null || piece.getId() == this.getPiece().getId()){
            this.piece = piece;
            this.setColor(this.piece.getColor());
            return true;
        }else{
            return false;
        }
    }


    Piece getPiece() {
        return piece;
    }

    void setY(int y) {
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
