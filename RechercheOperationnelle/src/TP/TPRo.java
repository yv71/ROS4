package TP;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TP.Algo.*;
import TP.Algo.Génétique.Genetique;
import TP.parseur.Parseur;
import TP.ville.Ville;

import java.util.ArrayList;

/**
 *
 * @author yv066840
 */
public class TPRo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parseur parseur = new Parseur();
        ArrayList<Ville> villes = parseur.parse();

        //Algo glouton
        //Algo glout = new Glouton(villes);
        //glout.run();


        //Algo plus proche voisin
       //Algo ppv = new PPV(villes);
      // ArrayList<Ville> cheminPPV = ppv.run();

       //Algo opti locale
        //Algo optiLocale = new Algo_OptiLocale(villes,cheminPPV);
        //optiLocale.run();

        //Algo génétique
        Algo genetique = new Genetique(villes);
        genetique.run();
    }

}
