/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author matam
 */
public enum Cukernatost {
    SLADKE("sladke"),
    POLOSLADKE("polosladke"),
    POLOSUCHE("polosuche"),
    SUCHE("suche");

    private final String cukernatost;

    private Cukernatost(String cukernatost) {
        this.cukernatost = cukernatost;
    }

    public String getCukernatost() {
        return cukernatost;
    }
}
