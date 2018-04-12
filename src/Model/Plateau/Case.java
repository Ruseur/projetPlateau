package Model.Plateau;

public class Case {
    int y;
    int x;
    Piece piece;

    public Case(int y, int x) {
        this.piece = null;
        this.y = y;
        this.x = x;
    }

    public void raz(){
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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
