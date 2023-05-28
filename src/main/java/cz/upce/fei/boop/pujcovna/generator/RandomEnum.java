/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.generator;

import java.util.Random;

/**
 *
 * @author matam
 */
public class RandomEnum<E> {

    Random rand = new Random();
    private final Class<E> enumTrida;

    public RandomEnum(Class<E> trida) {
        enumTrida = trida;
    }

    public E generujRandomEnum() {
        E[] values = enumTrida.getEnumConstants();
        return values[rand.nextInt(values.length)];
    }

}
