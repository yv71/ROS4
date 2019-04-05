package TP.Algo.Génétique;

import TP.Algo.Glouton;
import TP.ville.Ville;

import java.util.ArrayList;

public class Individu {
    private ArrayList<Ville> path;
    private double dist;

    public Individu(ArrayList<Ville> path) {
        this.path = new ArrayList<>(path);
        dist = 0;

    }

    public void calculDist(){
        Glouton g = new Glouton(path);
        g.run();
        dist = g.getDistMax();
    }

    public double getDist() {

        this.calculDist();
        return dist;
    }

    public ArrayList<Ville> getPath() {
        return path;
    }

    public Ville getVille(int indice){
        return this.path.get(indice);
    }

    public String toString() {
        return ""+this.getDist();
    }
}
