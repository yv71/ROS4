package TP.parseur;

import TP.ville.Ville;

public class Fabrique_Ville {
    //singleton
    private static Fabrique_Ville instance;

    //---------- CONSTRUCTEURS -----------------------------------------------------
    private Fabrique_Ville() {
    }
//------------------------------------------------------------------------------

    //---------- GETEUR/SETEUR -----------------------------------------------------
    public static Fabrique_Ville get() {
        if(instance == null) {
            instance = new Fabrique_Ville();
        }
        return instance;
    }
//------------------------------------------------------------------------------

    //construction d'une case
    public static Ville construireVille(String numero, String nom) {
        Ville rez = new Ville(Integer.parseInt(numero), nom);
        return rez;
    }
}
