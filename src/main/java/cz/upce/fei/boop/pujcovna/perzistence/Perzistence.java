/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.perzistence;

import cz.upce.fei.boop.pujcovna.data.BarvaVina;
import cz.upce.fei.boop.pujcovna.data.Cukernatost;
import cz.upce.fei.boop.pujcovna.data.Limo;
import cz.upce.fei.boop.pujcovna.data.Napoje;
import cz.upce.fei.boop.pujcovna.data.Pivo;
import cz.upce.fei.boop.pujcovna.data.Vino;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matam
 */
public class Perzistence {

    /**/
    /**
     * Metoda ulozi data z inputnuteho seznamu do binarniho souboru Metoda smaze
     * data v seznamu
     *
     * @param nazev nazev souboru, do ktereho chcete ukladat
     * @param sez Seznam dat, ktera chete ulozit
     * @throws java.io.IOException
     */
    public static void ulozDoBinarnihoSouboru(String nazev, Seznam<?> sez) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nazev);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Iterator<?> it = sez.iterator();

            while (it.hasNext()) {
                objectOutputStream.writeObject(it.next());
            }

            objectOutputStream.flush();
            fileOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Metoda nacita data z binarniho souboru podle inputnuteho nazvu a vraci je
     * ulozena v podobe Napoju ve Spojovem seznamu
     *
     * @param nazev nazev souboru, ze ktereho chcete nacitat
     * @return  seznam nactenych dat
     * @throws java.lang.ClassNotFoundException
     */
    public static Seznam<Napoje> zBinarnihoSoubor(String nazev) throws ClassNotFoundException {
        Seznam<Napoje> sez = new SpojovySeznam<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(nazev);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                try {
                    sez.vlozPosledni((Napoje) objectInputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(Perzistence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sez;
    }

    /**/
    /**
     * Metoda vlozi vsechna data ze inputnuteho spojoveho seznamu do textoveho
     * souboru Pokud soubor neexistuje, vytvori se novy v Proejktu
     *
     * @param nazev nazec Souboru, do ktereho chcete ukladat
     * @param sez Seznam ktery ukladate
     * @throws java.io.IOException
     */
    public static void ulozDoTextSouboru(String nazev, Seznam<?> sez) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(nazev));
        Iterator<?> it = sez.iterator();
        while (it.hasNext()) {
            writer.println(it.next().toString());
        }
        writer.close();
    }

    /**
     * Metoda nacte Napoje z textoveho souboru a vlozi je do spojoveho seznamu,
     * ktery nasladne vrati
     *
     * @param nazev nazev Souboru ze ktereho nacitame
     * @return Vraci Seznam Napoju
     * @throws java.io.IOException
     */
    public static Seznam<Napoje> zTextovehoSouboru(String nazev) throws IOException, Exception {
        Seznam<Napoje> napoje = new SpojovySeznam<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nazev));
            String radek;
            Seznam<String> vsechnaDataZaRovnaSe = new SpojovySeznam<>();
            try {while ((radek = reader.readLine()) != null) {
                String[] data = radek.split(",");
                for (String string : data) {
                    String[] dataZaRovnaSe = string.split("=");
                    if (!dataZaRovnaSe[0].trim().equals("id")) {
                        vsechnaDataZaRovnaSe.vlozPosledni(dataZaRovnaSe[1]);
                    }
                }
                String druh = vsechnaDataZaRovnaSe.odeberPrvni();
                if (druh.equals("PIVO")) {
                    napoje.vlozPosledni(new Pivo(
                            Double.parseDouble(vsechnaDataZaRovnaSe.odeberPrvni()),
                            Integer.parseInt(vsechnaDataZaRovnaSe.odeberPrvni()),
                            Double.parseDouble(vsechnaDataZaRovnaSe.odeberPrvni()),
                            Double.parseDouble(vsechnaDataZaRovnaSe.odeberPrvni())));
                }
                if (druh.equals("VINO")) {
                    napoje.vlozPosledni(new Vino(
                            Double.parseDouble(vsechnaDataZaRovnaSe.odeberPrvni()),
                            Integer.parseInt(vsechnaDataZaRovnaSe.odeberPrvni()),
                            Cukernatost.valueOf(vsechnaDataZaRovnaSe.odeberPrvni()),
                            BarvaVina.valueOf(vsechnaDataZaRovnaSe.odeberPrvni())));
                }
                if (druh.equals("LIMO")) {
                    napoje.vlozPosledni(new Limo(
                            Double.parseDouble(vsechnaDataZaRovnaSe.odeberPrvni()),
                            Integer.parseInt(vsechnaDataZaRovnaSe.odeberPrvni()),
                            vsechnaDataZaRovnaSe.odeberPosledni()));
                }
            }

            } catch (Exception ex) {
             throw new Exception();
        }
        }catch (FileNotFoundException | KolekceException ex) {
            Logger.getLogger(Perzistence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return napoje;
    }
}
