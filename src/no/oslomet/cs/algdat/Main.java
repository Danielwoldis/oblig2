package no.oslomet.cs.algdat;

import java.util.List;
import java.util.Objects;

public class Main extends DobbeltLenketListe{
    public static void main(String[] args) {
        //System.out.println("noe");

        Integer[] a = {null, 1, null};
        Objects.requireNonNull(a, "Tabellen har ingen verdier!");

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>(a);
        //System.out.println(liste.antall());

        Liste<String> liste1 = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " " + liste.tom());

    }
}





