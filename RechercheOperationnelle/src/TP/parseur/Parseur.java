/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP.parseur;

import TP.TPRo;
import TP.ville.CalculDistance;
import TP.ville.Ville;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yv066840
 */
public class Parseur {





    public ArrayList<Ville> parse() {

        ArrayList<Ville> rez = new ArrayList<>();


        //Fabrique de ville
        String fileVille = "resources/noms.csv";
        String fileCoord = "resources/villes.tsp";
        BufferedReader br = null;
        String line = "";
        String splitteur = " ";

        //Traitement construction villes
        try {
            File file = new File(TPRo.class.getResource(fileVille).getFile());
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] ville = line.split(splitteur);
                rez.add(Fabrique_Ville.construireVille(ville[0], ville[1]));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //Ajout lat/long
        try {
            File file = new File(TPRo.class.getResource(fileCoord).getFile());
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] coord = line.split(splitteur);
                rez.get(Integer.valueOf(coord[0])-1).setLattitude(coord[1]);
                rez.get(Integer.valueOf(coord[0])-1).setLongitude(coord[2]);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Ville v : rez){
            for (Ville v2 : rez){
                if(!v.equals(v2)){
                    v.putDist(v2, CalculDistance.CalculDist(v,v2));
                }
            }
        }
        return rez;
    }

}
