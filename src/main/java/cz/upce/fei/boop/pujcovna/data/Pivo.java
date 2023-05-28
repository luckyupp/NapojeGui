/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author matam
 */
public class Pivo extends Napoje {

    private final double pocetStupnu;
    private final double procentoAkolholu;

    public Pivo(double cena, int objem, double pocetStupnu, double procentoAlkoholu) {
        super(Druh.PIVO, cena, objem);
        this.pocetStupnu = pocetStupnu;
        this.procentoAkolholu = procentoAlkoholu;
    }

    public double getPocetStupnu() {
        return pocetStupnu;
    }

    public double getProcentoAkolholu() {
        return procentoAkolholu;
    }

    @Override
    public String toString() {
        return super.toString() + "pocetStupnu=" + pocetStupnu + ", procentoAkolholu=" + procentoAkolholu;
    }

}
