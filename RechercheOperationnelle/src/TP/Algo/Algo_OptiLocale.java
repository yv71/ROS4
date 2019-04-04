package TP.Algo;

import TP.ville.CalculDistance;
import TP.ville.Ville;

import java.util.ArrayList;
import java.util.Collections;

public class Algo_OptiLocale extends Algo{

    private ArrayList<Ville> path;
    public Algo_OptiLocale(ArrayList<Ville> villes, ArrayList<Ville> path) {
        super(villes);
        this.path = path;
    }

    @Override
    public ArrayList<Ville> run() {
        ArrayList<Ville> rez ;
        ArrayList<Ville> test = this.explore();
        this.addDist(this.gloutonDuPauvre(test));
        rez = test;
        System.out.println("Distance totale : " + this.getDistMax());
        path = rez;
        return rez;
    }

    public double gloutonDuPauvre(ArrayList<Ville> in){
        double rez = 0;
        for(int i = 0; i< in.size()-1; i++){
            rez += CalculDistance.CalculDist(in.get(i),in.get(i+1));
        }
        rez+= CalculDistance.CalculDist(in.get(0),in.get(in.size()-1));
        return rez;
    }

    public ArrayList<Ville> explore() {
        ArrayList<Ville> localPath ;
        ArrayList<Ville> rez = path;
        for(int i = 0; i < rez.size()-3;i++){
            localPath = new ArrayList<>();
            localPath.add(rez.get(i));
            localPath.add(rez.get(i+1));
            localPath.add(rez.get(i+2));
            localPath.add(rez.get(i+3));
            double dist = this.gloutonDuPauvre(localPath);
            Collections.swap(localPath,1,2);
            double newDist = this.gloutonDuPauvre(localPath);
            if (newDist < dist){
                Collections.swap(rez,i+1,i+2);
            }
        }
        return rez;
    }
}
