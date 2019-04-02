package TP.ville;

public class CalculDistance {

    public static double CalculDist(Ville v1, Ville v2){
        double rez = 0;
        double R = 6371;
        double lat1 = Math.toRadians(v1.getLattitude());
        double lat2 = Math.toRadians(v2.getLattitude());
        double difLat = Math.toRadians(v2.getLattitude()-v1.getLattitude());
        double difLong = Math.toRadians(v2.getLongitude()-v1.getLongitude());

        double a =Math.sin(difLat/2) * Math.sin(difLat/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(difLong/2) * Math.sin(difLong/2);

        double c = 2*Math.atan2(Math.sqrt(a) , Math.sqrt(1-a));
        rez = R * c;
        return rez;
    }
}
