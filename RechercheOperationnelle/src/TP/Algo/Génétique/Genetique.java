package TP.Algo.Génétique;

import TP.Algo.Algo;
import TP.Algo.Algo_OptiLocale;
import TP.ville.Ville;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetique extends Algo {
    private int nbVilles;
    private int nbGen = 100000;
    private int nbFirstGen = 1000;
    private int nbIndiv = 250;
    private int nbReprod =500;
    private int dureeMutLongMax = 20;
    private int nbMutLongueMax = 50;
    private int nbMutCourteMax = 50;
    private Algo_OptiLocale optiLocale;
    public Genetique(ArrayList<Ville> villes) {
        super(villes);
        this.nbVilles = villes.size();
        optiLocale = new Algo_OptiLocale(villes);
    }

    public ArrayList<Individu> premiereGeneration(){

        ArrayList<Individu> currentGen = new ArrayList<>();
        Individu shuffle;
        for(int i = 0 ; i < nbFirstGen; i++){
            Ville dijon = this.getVilles().get(0);
            shuffle = new Individu(this.getVilles());
            shuffle.getPath().remove(0);
            Collections.shuffle(shuffle.getPath());
            shuffle.getPath().add(0,dijon);
            currentGen.add(shuffle);
        }

        return currentGen;
    }

    public ArrayList<Ville> reproduction(ArrayList<Individu> currentGen) {
        Random r = new Random();
        int mere = r.nextInt(currentGen.size());
        int pere;
        do {
            pere = r.nextInt(currentGen.size());
        } while (pere == mere);

        int indicePere = r.nextInt((this.nbVilles) - 20);
        int dureePere = r.nextInt(this.nbVilles - indicePere - 2) + 1;
        Individu genMere = currentGen.get(mere);
        Individu genPere = currentGen.get(pere);

        ArrayList<Ville> newPath = new ArrayList<>();
        ArrayList<Ville> tronconPere = new ArrayList<>();

        for (int i = indicePere; i < indicePere + dureePere; i++) {
            tronconPere.add(genPere.getVille(i));
        }

        for (int i = 0; i < (this.nbVilles); i++) {
            Ville vMere = genMere.getVille(i);
            Ville dPere = tronconPere.get(0);
            if (tronconPere.contains(genMere.getVille(i))) {
                if (vMere == dPere) {
                    newPath.addAll(tronconPere);
                }
            } else {
                newPath.add(genMere.getVille(i));
            }

        }
        return newPath;
    }
    @Override
    public ArrayList<Ville> run() {

        Random r = new Random();
        //Première génération

        ArrayList<Individu> generation = this.premiereGeneration();


        //afficheGen(currentIndividu);
        int nb =0;
        while(nb < nbGen){
            for (Individu chemin: generation) {
                chemin.calculDist();
            }
            ArrayList<Individu> newGen = this.getBest(generation,nbIndiv);
            generation = new ArrayList<>(newGen);

            //Reproduction
            for(int nbIndiv = 0; nbIndiv < nbReprod; nbIndiv++){

                ArrayList<Ville> enfant = this.reproduction(generation);


                //Mutation
                /*Ville dijon = newPath.get(0);
                newPath.remove(dijon);

                int nbMutationCourte = r.nextInt(this.nbMutCourteMax);
                int nbMutationLongue = r.nextInt(this.nbMutLongueMax);
                int dureeMutationLongue = r.nextInt(this.dureeMutLongMax)+1;

                for(int i=0; i<nbMutationCourte; i++){
                    int idFirst = r.nextInt(this.nbVilles-1);
                    int idSecond = r.nextInt(this.nbVilles-1);
                    Collections.swap(newPath,idFirst,idSecond);
                }

                for(int i = 0; i<nbMutationLongue;i++){

                }
                **/
                //Collections.shuffle(newPath);
               // newPath.add(1,dijon);
                this.optiLocale.setPath(enfant);
                ArrayList<Ville> opti = optiLocale.run();
                enfant = opti;

                Individu nouvelIndividu = new Individu(enfant);
                generation.add(nouvelIndividu);
            }

            Individu meilleureDeSaGen = this.getBest(generation,1).get(0);
            System.out.println("Generation : " + nb + " Dist min : " + meilleureDeSaGen.getDist());
            nb++;

        }

        Individu meilleurePersonne = this.getBest(generation,1).get(0);
        System.out.println(meilleurePersonne.getDist());

        return meilleurePersonne.getPath();
    }

    public void afficheGen(ArrayList<Individu> individus){
        for (Individu chemin: individus) {
            System.out.println("Distance : " + chemin.getDist());
        }
    }

    public ArrayList<Individu> getBest(ArrayList<Individu> individus, int nbGen){
        ArrayList<Individu> newIndividu = new ArrayList<>();
        Collections.sort(individus,new Comparateur());
        for(int i=0;i<nbGen;i++) {
            newIndividu.add(individus.get(i));
        }
        /*ArrayList<Individu> gen = new ArrayList<>(individus);
        for(int i = 0; i < nbGen; i ++){
            Individu g0 = gen.get(0);
            double distMin = g0.getDist();
            for (Individu g:
                    gen) {
                if(g != g0){
                    if ( distMin > g.getDist()){
                        g0 =g;
                        distMin = g0.getDist();
                    }
                }
            }
            newIndividu.add(g0);
            gen.remove(g0);
        }*/
        return newIndividu;
    }
}
