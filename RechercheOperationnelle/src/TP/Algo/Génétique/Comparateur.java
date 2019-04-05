package TP.Algo.Génétique;

import java.util.Comparator;

public class Comparateur implements Comparator<Individu> {

    @Override
    public int compare(Individu o1, Individu o2) {
        if(o1.getDist() < o2.getDist()) {
            return -1;
        }
        if(o2.getDist() < o1.getDist()) {
            return 1;
        }
        return 0;
    }
}
