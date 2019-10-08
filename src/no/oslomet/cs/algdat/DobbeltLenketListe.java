package no.oslomet.cs.algdat;

////////////////// class DobbeltLenketListe //////////////////////////////

import jdk.nashorn.internal.runtime.regexp.joni.ast.AnyCharNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;

import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige;
        private Node neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    // hjelpemetoder
    private Node finnNode(int indeks) //fra kompendiet men funker ikke
    {
        Node<T> node = hode;
        for (int i = 0; i < indeks; i++){
            node = node.neste;
        }
        return node;
    }

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


    public DobbeltLenketListe() {
        //throw new NotImplementedException();
        Node<T> p = new Node<T>(null);
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a){
        //throw new NotImplementedException();
        if(a == null){ // skal egt bruke RequireNonNull
            throw new NullPointerException("Tabellen er tom!");
        }



        for(T t:a){
            if(t!=null){
                antall++;
            }
        }

        Node<T> temp= new Node<>(null);
        int tempAntall=0;
        int startverdi=0;
        for(T t:a){
            startverdi++;
            if(t!=null){
                hode=new Node<>(t);
                break;
            }

        }

        for(int i=startverdi;i<a.length;i++) {
            if (a[i] == null) {
                continue;
            }

            if (hode.neste==null){
                Node<T> ny = new Node<>(a[i]);
                hode.neste = ny;
                temp = ny;
                tempAntall++;


            } else if (tempAntall < antall) {
                Node<T> ny = new Node<>(a[i]);
                temp.neste=ny;
                ny.forrige=temp;
                temp=ny;
            }else{
                hale= new Node<>(a[i]);
                hale.forrige=temp;
            }
        }

    }

    public Liste<T> subliste(int fra, int til){
        //throw new NotImplementedException();
        fratilKontroll(antall, fra, til);

        DobbeltLenketListe<T> nyliste = new DobbeltLenketListe<>();
        if(fra == til) return nyliste;

        Node<T> node = new Node(finnNode(fra).verdi);
        nyliste.hode = node;
        nyliste.antall++;

        for (int i = fra+1; i < til; i++){
            Node<T> p = new Node(finnNode(i).verdi);

            p.forrige = node;
            node.neste = p;

            node = p;
            nyliste.antall++;
        }


        return nyliste;
    }

    @Override
    public int antall() {
        //throw new NotImplementedException();
        return antall;
    }

    @Override
    public boolean tom() {
        //throw new NotImplementedException();
        return antall() == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Kan ikke være null");
        if (antall()==0){
            hode= new Node<>(verdi);
            hale = new Node<>(verdi);
            antall++;
            return true;
        }else{
            Node<T> p = new Node<>(verdi);


            antall++;
            return true;
        }
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        if(indeks<=-1 || indeks>antall || verdi == null) return;
        if(tom()){
            hale=hode=new Node<>(verdi);
            antall++;
        } else if (antall==1){
            Node<T> node= new Node<>(verdi,null,hale);
            hode=node;
            antall++;
            endringer++;

        } else if(antall==indeks){
            leggInn(verdi);
        }else {
            Node<T> n = hode;
            for (int i = 0; i < indeks-1; i++) {
                n = n.neste;

            }
            Node<T> etter = n.neste;
            Node<T> inn = new Node<>(verdi, n, etter);
            antall++;
            endringer++;
        }
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi)!=-1;
    }

    @Override
    public T hent(int indeks) {
        //throw new NotImplementedException();
        indeksKontroll(indeks, false);
        return (T) finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> ny=hode;
        for(int i=0;i<antall;i++){
            if(ny.verdi.equals(verdi)){
                return i;
            }
            ny=ny.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        //throw new NotImplementedException();
        Objects.requireNonNull(nyverdi, "Ikke tillatt med nullverdier");
        indeksKontroll(indeks, false);
        Node<T> node = finnNode(indeks);
        T utverdi = node.verdi;
        node.verdi = nyverdi;
        endringer++;

        return utverdi;
    }

    @Override
    public boolean fjern(T verdi) {
        boolean endret = false;
        for (int i = 0; i < this.antall-1; i++){
            if (hent(i) == verdi){
                Node<T> node = finnNode(i);
                node.forrige.neste = node.neste;

                antall--;
                endringer++;

                endret = true;
            }
        }

        return endret;
    }

    @Override
    public T fjern(int indeks) {
        //throw new NotImplementedException();
        indeksKontroll(indeks, false);
        Node<T> node = finnNode(indeks);
        if (hode != node) node.forrige.neste = node.neste;
        if (hale != node) node.neste.forrige = node.forrige;

        antall--;
        endringer++;

        return node.verdi;
    }

    @Override
    public void nullstill() {
        Node<T> s = hode, p;

        while(s != null){
            p = s.neste;
            s.neste = null;
            s.verdi = null;
            s = p;
        }

        hode = hale = null;
        antall = 0;
    }

    @Override
    public String toString() {
        if(hode == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(!tom()){
            sb.append(hode.verdi);

         for(Node<T> p = hode.neste; p != null; p = p.neste){
             sb.append(",").append(" ").append(p.verdi);
         }
        }
        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        if(hode == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(!tom()){
            sb.append(hale.verdi);

         for(Node<T> p = hale.forrige; p != null; p = p.forrige){
             sb.append(",").append(" ").append(p.verdi);
         }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        //throw new NotImplementedException();
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new NotImplementedException();
        }

        @Override
        public void remove(){
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        for(int j = 0; j < liste.antall(); j++){
            for (int i = 0; i < liste.antall()-1; i++){
                if(c.compare(liste.hent(i), liste.hent(i + 1)) > 0){ // om "a" er større enn "b"
                    T temp = liste.hent(i);
                    liste.oppdater(i, liste.hent(i+1));
                    liste.oppdater(i+1, temp);
                }
            }
        }

    }

} // class DobbeltLenketListe


