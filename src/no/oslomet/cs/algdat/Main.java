package no.oslomet.cs.algdat;

import java.util.List;
import java.util.Objects;

public class Main extends DobbeltLenketListe{
    public static void main(String[] args) {
        //System.out.println("noe");

        Integer[] a = {1, null, 34, 2, 4, 6, 7, 3, 2};
        Objects.requireNonNull(a, "Tabellen har ingen verdier!");

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>(a);
        //System.out.println(liste.antall());

        Liste<String> liste1 = new DobbeltLenketListe<>();
        System.out.println(liste1.antall() + " " + liste1.tom());

    }
}





