package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Main extends DobbeltLenketListe{
    public static void main(String[] args) {
        //System.out.println("noe");
        Character[] characters = {'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I',
                'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B',
                'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A',
                'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I',
                'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L', 'B', 'R', 'A', 'S', 'I', 'L'};

        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};

        //DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(characters);
        /*
        String[] s1 = {};
        Integer[] s2 = {0,1,2,3};
        String[] s3 = {"A",null,"B","C","D","E"};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<Integer> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        `*/
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>(new Integer[]{1,2,4,5,67,7});
        Integer n=7;
        Integer n1=67;

        liste.fjern(n1);
        System.out.println(liste.fjern(null));
        System.out.println(liste.omvendtString());






//        System.out.println(liste.hent(2));
//
//
//        Character[] c = {'A','B','C','D','E','F','G','H','I','J'};
//        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
//        System.out.println(liste.subliste(3,8));  // [D, E, F, G, H]
//        System.out.println(liste.subliste(5,5));  // []
//        System.out.println(liste.subliste(8,liste.antall()));  // [I, J]   ​
//        //System.out.println(liste.subliste(0,11));  // skal kaste unntak
//        System.out.println(liste.subliste(6, 7));
//        */
//        Liste<String> liste1 = new DobbeltLenketListe<>(navn);
//        long tid = System.currentTimeMillis();
//        DobbeltLenketListe.sorter(liste1, Comparator.naturalOrder());
//        tid = System.currentTimeMillis() - tid;
//
//        //System.out.println(liste);
//        System.out.println(liste1);
//        System.out.println(tid);
    }
}





