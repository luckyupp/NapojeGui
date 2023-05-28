/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;

public class SpojovySeznam<E> implements Seznam<E> {

    private Node<E> prvniPrvek;
    private Node<E> aktualniPrvek;
    private Node<E> posledniPrvek;
    private int velikost;

    @Override
    public Iterator<E> iterator() {
        return new SpojovySeznamIterator();
    }

    public static <E> Collector<E, ?, SpojovySeznam<E>> toSpojovySeznam() {
        return Collector.of(
                SpojovySeznam::new,
                SpojovySeznam::vlozPosledni,
                (left, right) -> {
                    try {
                        left.addAll(right);
                    } catch (KolekceException ex) {
                        Logger.getLogger(SpojovySeznam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    @Override
    public void addAll(Seznam<E> right) throws KolekceException {
        while (right.size() > 0) {
            right.nastavPrvni();
            vlozPosledni(right.odeberAktualni());
        }
    }

    @Override
    public void aktualniSetNull() {
        aktualniPrvek = null;
    }

    private static class Node<E> {

        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public SpojovySeznam() {
        prvniPrvek = null;
        aktualniPrvek = null;
        velikost = 0;
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        exceptionJePrazdny();
        aktualniPrvek = prvniPrvek;
    }

    private void exceptionJePrazdny() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException("Seznam je prazdny.");
        }
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        exceptionJePrazdny();
        aktualniPrvek = posledniPrvek;
    }

    @Override
    public void dalsi() throws KolekceException {
        exceptionAktualniNull();

        if (aktualniPrvek == posledniPrvek) {
            throw new KolekceException("Následující prvek neexistuje.");
        }

        aktualniPrvek = aktualniPrvek.next;
    }

    private void exceptionAktualniNull() throws KolekceException {
        if (aktualniPrvek == null) {
            throw new KolekceException("Aktualni prvek neni nastaven.");
        }
    }

    @Override
    public boolean jeDalsi() {
        return aktualniPrvek.next != null;
    }

    @Override
    public void vlozPrvni(E data) {
        exceptionVlozenaDataNull(data);

        Node<E> novyPrvek = new Node<>(data);

        if (jePrazdny()) {
            posledniPrvek = novyPrvek;
        } else {
            novyPrvek.next = prvniPrvek;
        }
        prvniPrvek = novyPrvek;
        velikost++;
    }

    private void exceptionVlozenaDataNull(E data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Argument metody je null.");
        }
    }

    @Override
    public void vlozPosledni(E data) {
        exceptionVlozenaDataNull(data);

        Node<E> novyPosledniPrvek = new Node<>(data);

        if (jePrazdny()) {
            prvniPrvek = novyPosledniPrvek;
        } else {
            posledniPrvek.next = novyPosledniPrvek;
        }
        posledniPrvek = novyPosledniPrvek;
        velikost++;
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
        exceptionAktualniNull();
        exceptionVlozenaDataNull(data);

        Node<E> novyPrvek = new Node<>(data);

        if (aktualniPrvek == posledniPrvek) {
            aktualniPrvek.next = novyPrvek;
            novyPrvek.next = null;
            posledniPrvek = novyPrvek;
        } else {
            novyPrvek.next = aktualniPrvek.next;
            aktualniPrvek.next = novyPrvek;
        }
        velikost++;
    }

    @Override
    public boolean jePrazdny() {
        return prvniPrvek == null;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        exceptionJePrazdny();
        return prvniPrvek.data;
    }

    @Override
    public E dejPosledni() throws KolekceException {
        exceptionJePrazdny();
        return posledniPrvek.data;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        exceptionJePrazdny();
        exceptionAktualniNull();
        return aktualniPrvek.data;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        exceptionJePrazdny();
        exceptionAktualniNull();
        if (aktualniPrvek.next == null) {
            throw new KolekceException("");
        }
        return aktualniPrvek.next.data;

    }

    @Override
    public E odeberPrvni() throws KolekceException {
        exceptionJePrazdny();

        if (aktualniPrvek == prvniPrvek) {
            aktualniPrvek = null;
        }

        E dataOdebiranehoPrvku = prvniPrvek.data;
        if (velikost != 1) {
            prvniPrvek = prvniPrvek.next;
        } else {
            prvniPrvek = null;
            posledniPrvek = null;
        }

        velikost--;

        return dataOdebiranehoPrvku;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        exceptionJePrazdny();

        if (prvniPrvek.next == null) {
            return odeberPrvni();
        }

        if (aktualniPrvek == posledniPrvek) {
            aktualniPrvek = null;
        }

        Node<E> odebiranyPrvek = posledniPrvek;
        Node<E> predPosledniPrvek = prvniPrvek;
        while (predPosledniPrvek.next != posledniPrvek) {
            predPosledniPrvek = predPosledniPrvek.next;
        }
        predPosledniPrvek.next = null;
        posledniPrvek = predPosledniPrvek;
        velikost--;
        return odebiranyPrvek.data;

    }

    @Override
    public E odeberAktualni() throws KolekceException {
        exceptionJePrazdny();
        exceptionAktualniNull();

        E odebiranyPrvek = aktualniPrvek.data;
        if (aktualniPrvek == prvniPrvek) {
            return odeberPrvni();
        }
        if (aktualniPrvek == posledniPrvek) {
            return odeberPosledni();
        }

        Node<E> predAktualniPrvek = najdiPredAktualniPrvek(aktualniPrvek);
        predAktualniPrvek.next = aktualniPrvek.next;

        velikost--;
        aktualniPrvek = null;
        return odebiranyPrvek;
    }

    private Node<E> najdiPredAktualniPrvek(Node<E> node) {
        Node<E> pravyAktualniPrvek = prvniPrvek;
        while (pravyAktualniPrvek.next != node) {
            pravyAktualniPrvek = pravyAktualniPrvek.next;
        }
        return pravyAktualniPrvek;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        exceptionJePrazdny();
        exceptionAktualniNull();

        Node<E> nasledujiciPrvek = aktualniPrvek.next;

        if (nasledujiciPrvek == null) {
            throw new KolekceException("Nasledujici prvek neexistuje.");
        }

        aktualniPrvek.next = nasledujiciPrvek.next;
        nasledujiciPrvek.next = null;
        velikost--;
        return nasledujiciPrvek.data;
    }

    @Override
    public int size() {
        return velikost;
    }

    @Override
    public void zrus() {
        aktualniPrvek = null;
        prvniPrvek = null;
        posledniPrvek = null;
        velikost = 0;
    }

    private class SpojovySeznamIterator implements Iterator<E> {

        private Node<E> aktualni;

        public SpojovySeznamIterator() {
            aktualni = prvniPrvek;
        }

        @Override
        public boolean hasNext() {
            return aktualni != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E data = aktualni.data;
            aktualni = aktualni.next;
            return data;
        }

    }

}
