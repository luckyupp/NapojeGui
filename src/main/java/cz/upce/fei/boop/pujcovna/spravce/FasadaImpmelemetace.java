package cz.upce.fei.boop.pujcovna.spravce;

import cz.upce.fei.boop.pujcovna.data.BarvaVina;
import cz.upce.fei.boop.pujcovna.data.Cukernatost;
import cz.upce.fei.boop.pujcovna.data.Limo;
import cz.upce.fei.boop.pujcovna.data.Napoje;
import cz.upce.fei.boop.pujcovna.data.Pivo;
import cz.upce.fei.boop.pujcovna.data.Vino;
import cz.upce.fei.boop.pujcovna.generator.GeneratorBezReflexe;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import cz.upce.fei.boop.pujcovna.perzistence.Perzistence;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author matam
 */
public class FasadaImpmelemetace implements Ovladani {

    private Seznam<Napoje> seznam = new SpojovySeznam<>();
    private Seznam<Seznam<Napoje>> seznamSeznamu = new SpojovySeznam<>();

    @Override
    public Napoje vytvorPivoAVlozZaAktualni(Seznam<String> seznamAtributu) throws OvladaniException {
        try {
            Pivo napoj = new Pivo(Double.parseDouble(seznamAtributu.odeberPrvni()),
                    Integer.parseInt(seznamAtributu.odeberPrvni()),
                    Double.parseDouble(seznamAtributu.odeberPrvni()),
                    Double.parseDouble(seznamAtributu.odeberPrvni()));
            seznam.vlozZaAktualni(napoj);
            return napoj;
        } catch (KolekceException ex) {
            throw new OvladaniException("");
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }

    }

    @Override
    public Napoje vytvorLimooAVlozZaAktualni(Seznam<String> seznamAtributu) throws OvladaniException {
        try {
            Limo napoj = new Limo(Double.parseDouble(seznamAtributu.odeberPrvni()),
                    Integer.parseInt(seznamAtributu.odeberPrvni()),
                    seznamAtributu.odeberPrvni());
            seznam.vlozZaAktualni(napoj);
            return napoj;
        } catch (KolekceException ex) {
            throw new OvladaniException("");
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }

    }

    @Override
    public Napoje vytvorVinoAVlozZaAktualni(Seznam<String> seznamAtributu) throws OvladaniException {
        try {
            Vino napoj = new Vino(Double.parseDouble(seznamAtributu.odeberPrvni()),
                    Integer.parseInt(seznamAtributu.odeberPrvni()),
                    Cukernatost.valueOf(seznamAtributu.odeberPrvni()),
                    BarvaVina.valueOf(seznamAtributu.odeberPrvni()));
            seznam.vlozZaAktualni(napoj);
            return napoj;
        } catch (KolekceException ex) {
            throw new OvladaniException("");
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }
    }


    @Override
    public Seznam<Napoje> najdiNapojePodleAtributu(String atributJmeno, String hodnota) {
        Seznam<Napoje> filtrovany = seznam.stream()
                .filter(obj -> {
                    try {
                        Field atributuPotomka = obj.getClass().getDeclaredField(atributJmeno);                      //Pokud reflexe vychodi chybu nosuchfield exception zkusi se jeste atributy predka
                        atributuPotomka.setAccessible(true);
                        return atributuPotomka.get(obj).toString().equals(hodnota);                                 //Pokud se hodnota zadana uzivatelem rovna aktualni hodnote atributu lambda vrati true tzn, ze obj bude ve filtrovanem seznamu
                    } catch (NoSuchFieldException | SecurityException ex) {
                        try {
                            Field atributPrdeka = obj.getClass().getSuperclass().getDeclaredField(atributJmeno);    //Selhal potomek, zkusime jesta predka, pokud se vyhodi nosuchfield exception lambda vraci false tzn, ze obj nebude ve filtrovanem seznamu
                            atributPrdeka.setAccessible(true);
                            return atributPrdeka.get(obj).toString().equals(hodnota);
                        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex1) {
                            return false;
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        return false;
                    }
                })
                .collect(SpojovySeznam.toSpojovySeznam());

        return filtrovany;

    }

    @Override
    public Seznam<Napoje> najdiNapojePodleAtributuANastavJeNaSeznam(String atributJmeno, String hodnota) {
        seznam = najdiNapojePodleAtributu(atributJmeno, hodnota);
        return this.seznam;

    }

    @Override
    public void ulozModifikace() {
        seznamSeznamu.vlozPosledni(seznam);
    }

    @Override
    public Seznam<Napoje> dejAOdberPosledniModifikaci() {
        try {
            return seznamSeznamu.odeberPosledni();
        } catch (KolekceException ex) {
            Logger.getLogger(FasadaImpmelemetace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int sizeModifikaci() {
        return seznamSeznamu.size();
    }

    @Override
    public void odeberPodleAtributu(String atributJmeno, String hodnota) {
        Seznam<Napoje> filtrovany = seznam.stream()
                .filter(obj -> {
                    try {
                        Field atributuPotomka = obj.getClass().getDeclaredField(atributJmeno);
                        atributuPotomka.setAccessible(true);
                        return !atributuPotomka.get(obj).toString().equals(hodnota);
                    } catch (NoSuchFieldException | SecurityException ex) {
                        try {
                            Field atributPrdeka = obj.getClass().getSuperclass().getDeclaredField(atributJmeno);
                            atributPrdeka.setAccessible(true);
                            return !atributPrdeka.get(obj).toString().equals(hodnota);
                        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex1) {
                            return true;
                        }
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        return true;
                    }
                })
                .collect(SpojovySeznam.toSpojovySeznam());

        this.seznam = filtrovany;
    }

    public Seznam<Napoje> najdiNapojePodleAtributuBezReflexe(Comparator<Napoje> comp, Napoje key) {
        return seznam.stream()
                .filter(napoj -> comp.compare(napoj, key) == 0)
                .collect(SpojovySeznam.toSpojovySeznam());

    }

    @Override
    public void generuj(int pocetDat) {
        this.seznam = GeneratorBezReflexe.generuj(pocetDat);
    }

    @Override
    public Napoje dej() throws OvladaniException {
        try {
            return seznam.dejAktualni();
        } catch (KolekceException ex) {
            throw new OvladaniException("Chyba, seznam je prazdny, nebo aktualni neni nastaven");
        }
    }

    @Override
    public Napoje vyjmi() throws OvladaniException {
        try {
            return seznam.odeberAktualni();
        } catch (KolekceException ex) {
            throw new OvladaniException("Chyba, seznam je prazdny, nebo aktualni neni nastaven");
        }
    }

    @Override
    public void nastavPrvni() throws OvladaniException {
        try {
            seznam.nastavPrvni();
        } catch (KolekceException ex) {
            chybaSeznamJePrazdny();
        }
    }

    @Override
    public int pocet() {
        return seznam.size();
    }

    @Override
    public void clearAktualni() {
        seznam.aktualniSetNull();
    }

    @Override
    public Napoje odeberAktualni() throws OvladaniException {
        try {
            return seznam.odeberAktualni();
        } catch (KolekceException ex) {
            throw new OvladaniException("");
        }
    }

    @Override
    public void nastavPosledni() throws OvladaniException {
        try {
            seznam.nastavPosledni();
        } catch (KolekceException ex) {
            chybaSeznamJePrazdny();
        }
    }

    private void chybaSeznamJePrazdny() throws OvladaniException {
        throw new OvladaniException("Chyba seznam je prazdny");
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public void dalsi() throws OvladaniException {
        try {
            seznam.dalsi();
        } catch (KolekceException ex) {
            throw new OvladaniException("Chyba nastavovani seznamu");
        }
    }

    @Override
    public void zalohuj() throws OvladaniException {
        try {
            Perzistence.ulozDoBinarnihoSouboru("Zaloha.bin", seznam);
        } catch (IOException ex) {
            throw new OvladaniException("Chyba pri ukladani do souboru");
        }
    }

    @Override
    public void obnov() throws OvladaniException {
        try {
            seznam = Perzistence.zBinarnihoSoubor("Zaloha.bin");
        } catch (ClassNotFoundException ex) {
            throw new OvladaniException("Chyba pri obnovovani dat");
        }
    }

    @Override
    public void ulozText(String nazevSouboru) throws OvladaniException {
        try {
            Perzistence.ulozDoTextSouboru(nazevSouboru, seznam);
        } catch (IOException ex) {
            throw new OvladaniException("Chyba pri ukladani to textoveho souboru");
        }
    }

    @Override
    public void nactiText(String nazevSouboru) throws OvladaniException {
        try {
            this.seznam = Perzistence.zTextovehoSouboru(nazevSouboru);
        } catch (IOException e) {
            throw new OvladaniException("Chyba pri nacitani z textoveho souboru");
        } catch (Exception ex) {
            throw new OvladaniException("");
        }
    }

    @Override
    public Iterator<Napoje> iterator() {
        return seznam.iterator();
    }

    @Override
    public Seznam<Napoje> getSeznam() {
        return seznam;
    }

    @Override
    public Stream<Napoje> stream() {
        return seznam.stream();
    }

    @Override
    public void clearModifikace() {
        seznamSeznamu.zrus();
    }

    @Override
    public void addAll(Seznam<Napoje> sez) {
        try {
            seznam.addAll(sez);
        } catch (KolekceException ex) {
        }
    }

    @Override
    public void vytvorNapojAVlozMistoAktualni(Seznam<String> sez) throws OvladaniException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setAktualni(Napoje napoj) throws OvladaniException {
        try {
            boolean aktualniFound = true;
            seznam.nastavPrvni();
            while (aktualniFound) {
                if (seznam.dejAktualni().equals(napoj)) {
                    aktualniFound = false;
                } else {
                    seznam.dalsi();
                }
            }
        } catch (KolekceException ex) {
            throw new OvladaniException("");
        }
    }

}
