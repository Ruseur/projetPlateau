package view;

import Model.Plateau.Piece;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;

public class PieceView extends GridPane implements Observer {
    public PieceView(Piece piece) {
        int[][] disposition = piece.getDisposition();

        int columnIndex = 0;
        int rowIndex = 0;

        for(int[] colonne : disposition) {


            rowIndex = 0;
            for(int ligne : colonne) {
                if(ligne == 1) {
                    Rectangle rec = new Rectangle();
                    rec.setWidth(30);
                    rec.setHeight(30);
                    rec.setFill(piece.getColor());
                    rec.setStrokeWidth(0.1);
                    rec.setStroke(Color.WHITE);
                    this.add(rec, columnIndex, rowIndex);
                }
                rowIndex++;
            }
            columnIndex++;
        }


        piece.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
