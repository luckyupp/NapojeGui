/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.generator;

import cz.upce.fei.boop.pujcovna.data.BarvaVina;
import cz.upce.fei.boop.pujcovna.data.Cukernatost;
import cz.upce.fei.boop.pujcovna.data.Limo;
import cz.upce.fei.boop.pujcovna.data.Napoje;
import cz.upce.fei.boop.pujcovna.data.Pivo;
import cz.upce.fei.boop.pujcovna.data.Vino;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matam
 */
public class GeneratorBezReflexe { 

    private static final Random randInt = new Random();
    private static final RandomStringGenerator randomString = new RandomStringGenerator();
    private static final Random randDouble = new Random();

    public static Seznam<Napoje> generuj(int pocetGenerovanych) {
        try {
            Seznam<Napoje> vsechnyNapoje = new SpojovySeznam<>();
            int a;
            int b = 0;
            int c = 0;
            Seznam<Napoje> seznam = new SpojovySeznam<>();

            if (pocetGenerovanych >= 3) {
                a = randInt.nextInt(pocetGenerovanych - 2) + 1;
                b = randInt.nextInt(pocetGenerovanych - 1 - a) + 1;
                c = pocetGenerovanych - a - b;
            } else if (pocetGenerovanych == 2) {
                a = 1;
                b = 1;
                c = 0;
            } else {
                a = 1;
            }

            vsechnyNapoje.addAll(generujPivo(a));
            vsechnyNapoje.addAll(generujLimo(c));
            vsechnyNapoje.addAll(generujVino(b));

            return vsechnyNapoje;
        } catch (KolekceException ex) {
            Logger.getLogger(GeneratorBezReflexe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Seznam<Napoje> generujPivo(int pocetGenerovanych) {
        Seznam<Napoje> napoje = new SpojovySeznam<>();
        for (int i = 0; i < pocetGenerovanych; i++) {
            napoje.vlozPosledni(new Pivo(randDouble.nextDouble(10.00, 100.00), randDouble.nextInt(250, 2000), randDouble.nextDouble(7, 18), randDouble.nextDouble(3.50, 10.00)));
        }
        return napoje;
    }

    public static Seznam<Napoje> generujVino(int pocetGenerovanych) {
        Seznam<Napoje> napoje = new SpojovySeznam<>();
        for (int i = 0; i < pocetGenerovanych; i++) {
            napoje.vlozPosledni(new Vino(randDouble.nextDouble(10.00, 100.00), randDouble.nextInt(250, 2000), Cukernatost.class.getEnumConstants()[randInt.nextInt(3)], BarvaVina.class.getEnumConstants()[randInt.nextInt(2)]));
        }
        return napoje;
    }

    public static Seznam<Napoje> generujLimo(int pocetGenerovanych) {
        Seznam<Napoje> napoje = new SpojovySeznam<>();
        for (int i = 0; i < pocetGenerovanych; i++) {
            napoje.vlozPosledni(new Limo(randDouble.nextDouble(10.00, 100.00), randDouble.nextInt(250, 2000), randomString.generujRandomString(8)));
        }
        return napoje;
    }

    public static Napoje generujPodleDruhu(String druh) throws GeneratorException {
        try {
            switch (druh) {
                case "pivo", "p" -> {
                    return generujPivo(1).dejPrvni();
                }
                case "limo", "l" -> {
                    return generujLimo(1).dejPrvni();
                }
                case "vino", "v" -> {
                    return generujVino(1).dejPrvni();
                }
                default -> {
                    throw new GeneratorException("Chyba, spatny nazev druhu");
                }

            }
        } catch (KolekceException ex) {
            throw new GeneratorException("Chyba pri generovani");
        }
    }
}
