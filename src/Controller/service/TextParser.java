package Controller.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TextParser {
    private String filePath;

    private PrintWriter pw;
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
                lignes.add(line);
                line = br.readLine();
            }

            return lignes;

        } catch (Exception e){
            System.err.println("Erreur lecture de ligne: "+e.getMessage());
            e.printStackTrace();
            return null;
        }


    }



    public boolean write(ArrayList<String> lignes){
        try{
            this.pw = new PrintWriter(filePath);

            for(String ligne: lignes) {
                this.pw.println(ligne);
            }

            this.pw.close();
            return true;

        } catch (Exception e){
            System.err.println("Erreur lecture de ligne: "+e.getMessage());
            e.printStackTrace();
            return false;
        }


    }
    public String readLine(){
        return "Non implémenté";
    }
}
