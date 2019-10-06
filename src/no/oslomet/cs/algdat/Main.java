package no.oslomet.cs.algdat;

import java.util.List;
import java.util.Objects;

public class Main extends DobbeltLenketListe{
    public static void main(String[] args) {
        //System.out.println("noe");

        /*
        String[] s1 = {};
        Integer[] s2 = {0,1,2,3};
        String[] s3 = {"A",null,"B","C","D","E"};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<Integer> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        liste.leggInn(0, 4);  // ny verdi i tom liste
        liste.leggInn(0, 2);  // ny verdi legges forrest
        liste.leggInn(2, 6);  // ny verdi legges bakerst
        liste.leggInn(1, 3);  // ny verdi nest forrest
        liste.leggInn(3, 5);  // ny verdi nest bakerst
        liste.leggInn(0, 1);  // ny verdi forrest
        liste.leggInn(6, 7);  // ny verdi legges bakerst

        System.out.println(liste.antall());
        System.out.println(liste.toString());
         */


        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>(new Integer[]{1,2,3} );
        System.out.println(liste.hent(2));
    }
}





