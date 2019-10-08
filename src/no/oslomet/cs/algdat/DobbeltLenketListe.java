package no.oslomet.cs.algdat;

////////////////// class DobbeltLenketListe //////////////////////////////

import jdk.nashorn.internal.runtime.regexp.joni.ast.AnyCharNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

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
        Node<T> p = new Node<T>(null);
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a){
        if(a == null){
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
        fratilKontroll(antall, fra, til);

        DobbeltLenketListe<T> nyliste = new DobbeltLenketListe<>();
        if(fra == til) return nyliste;

        Node<T> node = new Node<>(finnNode(fra).verdi);
        nyliste.hode = node;
        nyliste.antall++;

        for (int i = fra+1; i < til; i++){
            Node<T> p = new Node<>(finnNode(i).verdi);

            p.forrige = node;
            node.neste = p;

            node = p;
            nyliste.antall++;
        }
        return nyliste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall() == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        if(verdi==null) throw new NullPointerException();
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
        if(indeks<=-1 || indeks>antall ) throw new  IndexOutOfBoundsException();
        if(verdi==null) throw new NullPointerException();
        if(tom()){
            hale=hode=new Node<>(verdi);
            antall++;
            endringer++;
        } else if (antall==1){
            hode= new Node<>(verdi,null,hale);
            hale.forrige=hode;
            antall++;
            endringer++;

        } else if(antall==indeks){
            leggInn(verdi);
        }else {
            if (indeks == 0) {
                Node<T> n = finnNode(0);
                hode = new Node<>(verdi, null, n);
                hode.neste = n;
                n.forrige = hode;


            } else {
                Node<T> node = finnNode(indeks);
                Node<T> nodef = node.forrige;
                Node<T> ny = new Node<>(verdi, nodef, node);
                node.forrige = ny;
                nodef.neste = ny;
            }
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
        Node<T> n=hode;

        if(antall == 1) {
            hode = hale = null;
            antall--;
            endringer++;
            return true;
        }
        if (n.verdi.equals(verdi)) {
            hode = hode.neste;
            hode.forrige = null;
            antall--;
            endringer++;
            return true;
        }
        n=n.neste;

        for (int i = 1; i < antall-1; i++){
            if (n.verdi.equals(verdi)){
                n.forrige.neste = n.neste;
                n.neste.forrige = n.forrige;
                antall--;
                endringer++;
                return true;

            }
            n=n.neste;
        }
        if(hale.verdi.equals(verdi)){
            hale = hale.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return true;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> node = finnNode(indeks);
        Node<T> ut = node;
        if (!hode.equals(node)) node.forrige.neste = node.neste;
        if (!hale.equals(node)) node.neste.forrige = node.forrige;
        if(antall == 1){
            hode = hale = null;
            //hode.forrige = hale.neste = null;
        }else if(hode.equals(node)){
            hode = hode.neste;
            hode.forrige = null;
        }else if(hale.equals(node)){
            hale = hale.forrige;
            hale.neste = null;
        }

        antall--;
        endringer++;

        return ut.verdi;
        //funker ikke
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
        endringer = 0;
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
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new  DobbeltLenketListeIterator(indeks);
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
            indeksKontroll(indeks, false);
            Node<T> node = new Node<>(hent(indeks));
            denne = node;
            fjernOK = false;
            iteratorendringer = endringer;
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
            if(!fjernOK){
                throw new IllegalStateException("Feil ved fjerning av verdi");
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


