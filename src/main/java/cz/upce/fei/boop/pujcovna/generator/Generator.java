/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.generator;

import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 *
 * @author matam
 */
public class Generator<E> {

    private static final Random randInt = new Random();
    private static final RandomStringGenerator randomString = new RandomStringGenerator();
    private static final Random randByte = new Random();
    private static final Random randShort = new Random();
    private static final Random randChar = new Random();
    private static final Random randLong = new Random();
    private static final Random randDouble = new Random();
    private static final Random randBolean = new Random();
    private static final Random randCFloat = new Random();

    private final Class<E> trida;

    public Generator(Class<E> trida) {
        this.trida = trida;
    }

    public SpojovySeznam<E> generuj(int pocetGenerovanychObjektu) throws GeneratorException {

        Constructor<?>[] konstruktory = trida.getDeclaredConstructors();                //reflexe, ziska vsechny konstruktory do pole
        Constructor<?> konstruktor = konstruktory[0];                                   //my si vezmeme prvni konstruktor do promenne konstruktor

        SpojovySeznam<E> seznam = new SpojovySeznam<>();                                //vytvoreni seznamu, do ktereho budeme nasledne pridavat prvky a nakonec ho returneme

        for (int i = 0; i < pocetGenerovanychObjektu; i++) {
            try {
                seznam.vlozPosledni((E) konstruktor.newInstance(generujPoleInputParametru(konstruktor)));    //for podle poctu generovanych objektu vytvori a vlozi instance do seznamu
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new GeneratorException("Chyba vytvareni instance");
            }
        }

        return seznam;

    }

    private Object[] generujPoleInputParametru(Constructor<?> konstruktor) throws GeneratorException {

        Object[] poleInputParametru = new Object[konstruktor.getParameterCount()];       //vytvoreni pole objektu, do ktereho budeme nasledne ukladat vsechny vygenerovane atributy ruznych typu, nakonec ho vratime
        Class<?>[] poleTridInputParametru = konstruktor.getParameterTypes();             //reflexe, ziska vsechny datove typy atributů objektu typu class, my je ulozime a nasledne na zaklade techto trid budeme generovat nahodne hodnoty

        int i = 0;
        for (Class<?> tridaS : poleTridInputParametru) {                                 // foreach pro kazdou tridu z pole ziskanzch trid vygeneruje pomoci switche nahodnou hodnotu a ulozi ji do pole poleInputParametru, vcetne enum, vcetne skladanych objektu
            if (tridaS.isEnum()) {
                RandomEnum<?> randomEnum = new RandomEnum<>(tridaS);
                poleInputParametru[i++] = randomEnum.generujRandomEnum();
            } else if (tridaS.isPrimitive()) {
                switch (tridaS.getName()) {
                    case "boolean":
                        poleInputParametru[i++] = randBolean.nextBoolean();
                        break;
                    case "byte":
                        poleInputParametru[i++] = (byte) randByte.nextInt(Byte.MAX_VALUE + 1);
                        break;
                    case "char":
                        poleInputParametru[i++] = (char) randChar.nextInt(Character.MAX_VALUE + 1);
                        ;
                        break;
                    case "short":
                        poleInputParametru[i++] = (short) randShort.nextInt(Short.MAX_VALUE + 1);
                        break;
                    case "int":
                        poleInputParametru[i++] = randInt.nextInt();
                        break;
                    case "long":
                        poleInputParametru[i++] = randLong.nextLong();
                        break;
                    case "float":
                        poleInputParametru[i++] = randCFloat.nextFloat();
                        break;
                    case "double":
                        poleInputParametru[i++] = randDouble.nextDouble();
                        break;

                }

            } else if (tridaS == String.class) {
                poleInputParametru[i++] = randomString.generujRandomString(6);
            } else {
                poleInputParametru[i++] = dejRandomObj(tridaS);                           //umi generovat i objekty slozene z dlasich neprimitvnich objektu
            }

        }
        return poleInputParametru;

    }

    private Object dejRandomObj(Class<?> tridaObj) throws GeneratorException {                     // private, pouzivame pro generovani slozenzch objektu vraci referenci, kterou nasledne v metode generuj pole parametru ulozime do poleInputPaaremetru, ktere pak predaveme konstruktoru

        Constructor<?>[] konstruktory = tridaObj.getDeclaredConstructors();              //ziskame konstruktory
        Constructor<?> konstruktor = konstruktory[0];                                    //vezmeme si prvni
        try {
            return konstruktor.newInstance(generujPoleInputParametru(konstruktor));          //vytvorime instanci dane inputnute tridy a vratime ji
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new GeneratorException("Chyba vytvareni instance");
        }
    }

}
