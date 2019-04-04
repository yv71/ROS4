package TP.Algo.Génétique;

import TP.Algo.Glouton;
import TP.ville.Ville;

import java.util.ArrayList;

public class Generation {
    private ArrayList<Ville> path;
    private double dist;

    public Generation(ArrayList<Ville> path) {
        this.path = new ArrayList<>(path);
        dist = 0;
    }

    public void calculDist(){
        Glouton g = new Glouton(path);
        g.run();
        dist = g.getDistMax();
    }

    public double getDist() {
        return dist;
    }

    public ArrayList<Ville> getPath() {
        return path;
    }

    public Ville getVille(int indice){
        return this.path.get(indice);
    }
}
