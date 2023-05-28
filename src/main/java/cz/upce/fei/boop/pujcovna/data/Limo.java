/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author matam
 */
public class Limo extends Napoje {

    private final String prichut;

    public Limo(double cena, int objem, String prichut) {
        super(Druh.LIMO, cena, objem);
        this.prichut = prichut;
    }

    public String getPrichut() {
        return prichut;
    }

    @Override
    public String toString() {
        return super.toString() + "prichut=" + prichut;
    }

}
