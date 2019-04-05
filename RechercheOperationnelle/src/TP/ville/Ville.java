package TP.ville;

import java.util.HashMap;
import java.util.Objects;

public class Ville {
    private int numero;
    private String nom;
    private double lattitude;
    private double longitude;
    private HashMap<Ville, Double> distanceTo;

    public Ville(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
        this.distanceTo = new HashMap<>();
       // distanceTo.put(this,0.00);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = Double.parseDouble(lattitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = Double.parseDouble(longitude);
    }

    public void putDist(Ville v, double dist){
        this.distanceTo.put(v,dist);
    }



    public double getDistTo(Ville v){
        return this.distanceTo.get(v);
    }
    @Override
    public String toString() {
        return "Num :" + numero + " Nom : " + nom + " Lat : " + lattitude + " Long : " + longitude;
    }

    public void removeVille(Ville v){
        this.distanceTo.remove(v);
    }

    public Ville distMin(){
        double dist = 10000;
        Ville rez = this;
        for(Ville v : this.distanceTo.keySet()){
            if (dist > this.distanceTo.get(v)){
                dist = this.distanceTo.get(v);
                rez = v;
            }
        }
        return rez;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return numero == ville.numero &&
                Double.compare(ville.lattitude, lattitude) == 0 &&
                Double.compare(ville.longitude, longitude) == 0 &&
                Objects.equals(nom, ville.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, nom, lattitude, longitude);
    }
}
