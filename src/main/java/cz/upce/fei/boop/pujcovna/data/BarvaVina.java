/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author matam
 */
public enum BarvaVina {
    BILE("bile"),
    RUZOVE("ruzove"),
    CERVENE("cervene");

    private final String barva;

    private BarvaVina(String barva) {
        this.barva = barva;
    }

    public String getBarvaVina() {
        return barva;
    }
}
