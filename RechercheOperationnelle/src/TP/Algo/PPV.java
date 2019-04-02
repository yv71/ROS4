package TP.Algo;

import TP.ville.CalculDistance;
import TP.ville.Ville;

import java.util.ArrayList;

public class PPV extends Algo{
    public PPV(ArrayList<Ville> villes) {
        super(villes);
    }

    @Override
    public ArrayList<Ville> run() {
        ArrayList<Ville> rez = new ArrayList<>();
        Ville v = this.getVilles().get(0);
        rez.add(v);
        this.getVisited().put(v,true);
        Ville vSuivante= null;
        double dist;
        while(this.unvisited()){
            boolean ok = false;
            while(!ok){
                vSuivante = v.distMin();
                if(this.getVisited().get(vSuivante)){
                    v.removeVille(vSuivante);
                }
                else{
                    ok = true;
                }
            }
            dist= v.getDistTo(vSuivante);
            this.addDist(dist);
            v = vSuivante;
            this.getVisited().put(v,true);
            rez.add(v);
        }
        this.addDist(CalculDistance.CalculDist(rez.get(0),rez.get(rez.size()-1)));
        System.out.println(this);
        for (Ville vAffiche : rez
             ) {
            System.out.println("Prochaine ville du chemin : " + vAffiche);
        }
        return rez;
    }

    @Override
    public String toString() {
        return "Distance avec la méthode du plus proche voisin : "  + this.getDistMax();
    }
}
