package TP.Algo.Génétique;

import TP.Algo.Algo;
import TP.ville.Ville;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetique extends Algo {
    private int nbVilles;
    public Genetique(ArrayList<Ville> villes) {
        super(villes);
        this.nbVilles = villes.size();
    }

    @Override
    public ArrayList<Ville> run() {
        ArrayList<Generation> currentGeneration = new ArrayList<>();
        Generation shuffle;
        Random r = new Random();
        //Première génération
        for(int i = 0 ; i < 50; i++){
            Ville dijon = this.getVilles().get(0);
            shuffle = new Generation(this.getVilles());
            shuffle.getPath().remove(0);
            Collections.shuffle(shuffle.getPath());
            shuffle.getPath().add(0,dijon);
            currentGeneration.add(shuffle);
        }
        for (Generation chemin: currentGeneration) {
            chemin.calculDist();
        }
        ArrayList<Generation> newGen = this.getBest(currentGeneration,15);
        currentGeneration = new ArrayList<>(newGen);

        //afficheGen(currentGeneration);

        //Reproduction
        int mere = r.nextInt(currentGeneration.size());
        int pere;
        do {
            pere = r.nextInt(currentGeneration.size());
        }while(mere == pere);


        int indicePere = r.nextInt((this.nbVilles)-20);
        int dureePere = r.nextInt(indicePere+1);
        Generation genMere = currentGeneration.get(mere);
        Generation genPere = currentGeneration.get(pere);
        Generation nouvelleGen;
        ArrayList<Ville> newPath = new ArrayList<>();

        for(int i = 0; i < (this.nbVilles);i++){
           if(i == dureePere){
                for(int j = 0; j < dureePere-1; j ++){
                    if(!newPath.contains(genPere.getVille(i+j))){
                        newPath.add(genPere.getVille(i+j));
                    }
                }
            }
            if(!newPath.contains(genMere.getVille(i))){
                newPath.add(genMere.getVille(i));
            }
        }
        int dist = 0;
        for(int i = 1; i < 67; i++){
            dist += i;
        }
        int distv = 0;
        for(Ville v : newPath){
            distv += v.getNumero();
        }
        System.out.println(newPath.size());
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
