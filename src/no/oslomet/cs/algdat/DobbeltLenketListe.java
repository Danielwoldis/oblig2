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
    private Node<T> finnNode(int indeks)
    {
        Node<T> node;
        if(indeks < antall/2){
            node = hode;
            for (int i = 0; i < indeks; i++){
                node = node.neste;
            }
        }else {
            node = hale;
            for (int i = antall-1; i > indeks; i--){
                node = node.forrige;
            }
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

        for (T t : a) {
            if (t == null) {
                continue;
            }
            if (hode == null) {
                temp = new Node<>(t);
                hode = temp;

            } else {
                Node<T> ny = new Node<>(t, temp, null);
                temp.neste = ny;
                temp = ny;
            }
        }
        hale=temp;
    }

    public Liste<T> subliste(int fra, int til){
        //throw new NotImplementedException();
        fratilKontroll(antall, fra, til);

        DobbeltLenketListe<T> nyliste = new DobbeltLenketListe<>();
        Node<T> node = new Node<>((T) finnNode(fra).verdi);
        nyliste.hode = node;


        for (int i = fra+1; i < til; i++){ //noe galt i for tror jeg
            Node<T> p = new Node<>((T) finnNode(i).verdi);

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
        if(verdi==null) return false; // egentlig object.req...
        if (antall()==0){
            Node<T> ny= new Node<>(verdi);
            hode=ny;
            hale=ny;
            antall++;
            endringer++;

            return true;
        }else{
            hale.neste=new Node<>(verdi,hale,null);
            hale=hale.neste;
            antall++;
            endringer++;

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
            if(denne == null){
                throw new NoSuchElementException("Listen er tom");
            }
            
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("Noe har blitt endret");
            }
            
            fjernOK = true;
            
            T verdi = denne.verdi;
            denne = denne.neste;
            
            return verdi;
        }

        @Override
        public void remove(){
            public void remove(){
            if(!fjernOK){
                throw new IllegalStateException("Feil ved fjerning verdi nå");
            }
            
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("Listen har blitt endret");
            }
            
            fjernOK = false;
            Node<T> p = hode;
            
            if(antall == 1){
                hode = hale = null;
            }
            
            else if(denne == null){
                p = hale;
                hale = hale.forrige;
                hale.neste = null;
            }
            
            else if(denne.forrige == hode){
                hode = hode.neste;
                hode.forrige = null;
            }
            
            else{
                p = denne.forrige;
                p.forrige.neste = p.neste;
                p.neste.forrige = p.forrige;
            }
            
            p.verdi = null;
            p.forrige = p.neste = null;
            antall--;
                
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


