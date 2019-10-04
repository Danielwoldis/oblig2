package no.oslomet.cs.algdat;

public class Main{
    public static void main(String[] args) {
        String[] s1 = {}, s2 = {"A","B"}, s3 = {null,"A",null,"B",null};

        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);


        System.out.println(l2.toString());



    }
}





