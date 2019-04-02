package TP.Algo;

import TP.ville.Ville;

import java.util.ArrayList;

public class Glouton extends Algo{

    public Glouton(ArrayList<Ville> villes) {
        super(villes);
    }

    @Override
    public ArrayList<Ville> run() {
        Ville v = null;
        Ville v2 = null;
        for(int i = 0; i < this.getVilles().size(); i++){
            v = this.getVilles().get(i);
            if(i != this.getVilles().size()-1){
                v2 = this.getVilles().get(i+1);
            }
            else {
                v2 = this.getVilles().get(0);
            }
            this.addDist(v.getDistTo(v2));
            this.getVisited().put(v2,true);
        }
        //System.out.println(this);
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Distance avec la méthode glouton : "  + this.getDistMax();
    }
}
