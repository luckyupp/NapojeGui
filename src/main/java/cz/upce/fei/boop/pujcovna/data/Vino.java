/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author matam
 */
public class Vino extends Napoje {

    private final Cukernatost cukernatost;
    private final BarvaVina barva;

    public Vino(double cena, int objem, Cukernatost cukernatost, BarvaVina barva) {
        super(Druh.VINO, cena, objem);
        this.cukernatost = cukernatost;
        this.barva = barva;
    }

    public Cukernatost getCukernatost() {
        return cukernatost;
    }

    public BarvaVina getBarva() {
        return barva;
    }

    @Override
    public String toString() {
        return super.toString() + "cukernatost=" + cukernatost + ", barva=" + barva;
    }

}
