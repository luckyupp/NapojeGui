package cz.upce.fei.boop.pujcovna.data;

import java.io.Serializable;

public abstract class Napoje implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Druh druh;
    private final double cena;
    private final int objem;
    private static int pocetVytvorenychNapoju = 0;
    private final int id;

    public Napoje(Druh druh, double cena, int objem) {
        this.druh = druh;
        this.cena = cena;
        this.objem = objem;
        this.id = ++pocetVytvorenychNapoju;
    }

    public Druh getDruh() {
        return druh;
    }

    public double getCena() {
        return cena;
    }

    public int getObjem() {
        return objem;
    }

    public int getId() {
        return id;
    }

    public Napoje getNapoj() {
        return this;
    }

    @Override
    public String toString() {
        return "druh=" + druh + ", cena=" + cena + ", objem=" + objem + ", id=" + id + ", ";
    }

}
