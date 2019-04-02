package TP.Algo.Génétique;

import TP.Algo.Algo;
import TP.ville.Ville;

import java.util.ArrayList;
import java.util.Collections;

public class Genetique extends Algo {
    public Genetique(ArrayList<Ville> villes) {
        super(villes);
    }

    @Override
    public ArrayList<Ville> run() {
        ArrayList<Generation> currentGeneration = new ArrayList<>();
        Generation shuffle;
        for(int i = 0 ; i < 5; i++){
            shuffle = new Generation(this.getVilles());
            Collections.shuffle(shuffle.getPath());
            currentGeneration.add(shuffle);
        }
        for (Generation chemin: currentGeneration) {
            chemin.calculDist();
            System.out.println("Distance : " + chemin.getDist());
        }
        ArrayList<Generation> newGen = this.getBest(currentGeneration,2);
        currentGeneration = new ArrayList<>(newGen);

        System.out.println(currentGeneration.size());
        afficheGen(currentGeneration);
        return null;
    }

    public void afficheGen(ArrayList<Generation> generations){
        for (Generation chemin: generations) {
            System.out.println("Distance : " + chemin.getDist());
        }
    }

    public ArrayList<Generation> getBest(ArrayList<Generation> generations, int nbGen){
        ArrayList<Generation> newGeneration = new ArrayList<>();
        ArrayList<Generation> gen = new ArrayList<>(generations);
        for(int i = 0; i < nbGen; i ++){
            Generation g0 = gen.get(0);
            double distMin = g0.getDist();
            for (Generation g:
                    gen) {
                if(g != g0){
                    if ( distMin > g.getDist()){
                        g0 =g;
                        distMin = g0.getDist();
                    }
                }
            }
            newGeneration.add(g0);
            gen.remove(g0);
        }
        return newGeneration;
    }
}
