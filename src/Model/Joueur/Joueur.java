package Model.Joueur;

import java.util.Observable;

public class Joueur extends Observable {
    private String nom;

    public Joueur(String nom){
        this.setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
