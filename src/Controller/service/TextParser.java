package Controller.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextParser {
    private String filePath;

    private FileReader fr;
    private BufferedReader br;

    public TextParser(String filePath) {
        this.filePath = filePath;

        try{
            this.fr = new FileReader(filePath);

            try{
                BufferedReader br = new BufferedReader(fr);
                this.br = br;

            } catch (Exception e){
                System.err.println("Problème buffered reader: "+e.getMessage());
            }

        }catch (Exception e){
            System.err.println("Fichier non trouvé: "+e.getMessage());
        }
    }



    public ArrayList<String> readAll(){
        ArrayList<String> lignes = new ArrayList<>();
        try{

            String line = br.readLine();
            while (line != null) {
                line = br.readLine();
                lignes.add(line);
            }

            return lignes;

        } catch (Exception e){
            System.err.println("Erreur lecture de ligne: "+e.getMessage());
            e.printStackTrace();
            return null;
        }


    }
    public String readLine(){
        return "Non implémenté";
    }
}
