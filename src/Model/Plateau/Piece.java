package Model.Plateau;

import java.util.ArrayList;

public class Piece {

    private int[][] disposition;
    private ArrayList<Case> listCase;

    public Piece(int[][] disposition) { //v2: mettre un nom et chercher la reference dans un json
        this.disposition = disposition;
        this.listCase = new ArrayList<>();
    }

    public ArrayList<Case> getListCase() {
        return listCase;
    }

    public void afficherDisposition(){
        for(int[] ligne: disposition){
            for(int i: ligne){
                System.out.print(i +" | ");
            }
            System.out.println();
        }
    }
}
