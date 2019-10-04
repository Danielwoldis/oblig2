package no.oslomet.cs.algdat;

////////////////// class DobbeltLenketListe //////////////////////////////

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

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

    public DobbeltLenketListe() {
        //throw new NotImplementedException();
        Node<T> p = new Node<T>(null);
    }

    public DobbeltLenketListe(T[] a){
        //throw new NotImplementedException();
        if(a == null){ // skal egt bruke RequireNonNull
            throw new NullPointerException("Tabellen er tom!");
        }

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<T>();

        Node<T> p = new Node<T>(null);
        liste.hode = p;

        int startverdi = 0;

        for(T t: a){ // finner første objekt i tabellen som ikke er null
            if (t != null){
                p = new Node<T>(t);
                break;
            }
            startverdi++;
        }

        for (int i = startverdi; i < a.length; i++){
            if(a[i] != null){
                antall++;
                Node<T> q = new Node<T>(a[i]);
                q.forrige = p;
                p.neste = q;

                p = q;
                liste.hale = q;
            }

        }
    }

    public Liste<T> subliste(int fra, int til){
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T hent(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
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
        throw new NotImplementedException();
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
            throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


