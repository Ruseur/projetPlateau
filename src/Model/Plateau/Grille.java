package Model.Plateau;

public class Grille {

    private Case[][] grilleCase;

    public Grille(int largeurPlateau, int hauteurPlateau) {
        this.grilleCase = new Case[hauteurPlateau][largeurPlateau];

        for(int i  = 0; i < hauteurPlateau; i++){
            for(int j  = 0; j < largeurPlateau; j++){
                this.grilleCase[i][j] = new Case(i,j);
            }
        }
    }

    public Case[][] getGrilleCase() {
        return grilleCase;
    }
}
