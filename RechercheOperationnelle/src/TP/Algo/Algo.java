package TP.Algo;

import TP.ville.Ville;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Algo {
    private HashMap<Ville, Boolean> visited;
    private ArrayList<Ville> villes;
    private double distMax;

    public Algo(ArrayList<Ville> villes) {
        this.visited = new HashMap<>();
        this.villes = villes;
        for(Ville v : villes){
            visited.put(v,false);
        }
        distMax = 0;
    }

    public HashMap<Ville, Boolean> getVisited() {
        return visited;
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public void addDist(double d){
        this.distMax += d;
    }

    public abstract ArrayList<Ville> run();

    public double getDistMax() {
        return this.distMax;
    }

    public boolean unvisited(){
        boolean b =this.getVisited().values().contains(false);
        return b;
    }

}
