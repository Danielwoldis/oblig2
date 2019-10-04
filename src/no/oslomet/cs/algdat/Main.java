package no.oslomet.cs.algdat;

import java.util.List;
import java.util.Objects;

public class Main extends DobbeltLenketListe{
    public static void main(String[] args) {
        //System.out.println("noe");

        String[] s1 = {}, s2 = {"A"}, s3 = {"A",null,"B","C","D","E"};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        System.out.println(l3.omvendtString());
        System.out.println("sssss");
//        System.out.println(l2.toString());
//        System.out.println(l1.toString());
//        System.out.println(l1.omvendtString());
//        System.out.println(l2.omvendtString());
//        System.out.println(l3.omvendtString());


        System.out.println(liste.toString());
        liste.leggInn(4);
        System.out.println(liste.toString());
    }
}





