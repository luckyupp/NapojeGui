/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.data;

/**
 *
 * @author st69587
 */
public enum Druh {
    PIVO("pivo"),
    LIMO("limo"),
    VINO("vino");

    private final String druh;

    private Druh(String druh) {
        this.druh = druh;
    }

    public String getDruh() {
        return druh.toLowerCase();
    }
}
