package cz.upce.fei.boop.pujcovna.spravce;

import cz.upce.fei.boop.pujcovna.data.Napoje;
import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import java.util.stream.Stream;

/**
 *
 * @author matam
 */
public interface Ovladani extends Iterable<Napoje> {

    /**
     * Metoda prejde na dalsi data v atributu seznam
     *
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void dalsi() throws OvladaniException;

    /**
     *
     * @return Vrati odkaz na Napoj na aktualni pozici na atributu seznam
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    Napoje dej() throws OvladaniException;

    /**
     * Metoda nahodne vygeneruje Napoje na zaklade inputnuteho poctu Napoju a
     * ulozi je do atributu seznam
     *
     * @param pocetDat Pocet kolik dat chcete vygenerovat
     */
    void generuj(int pocetDat);

    /**
     * Metoda najde vsechny Napoje v atribudu seznam podle input parametru a
     * vrati je
     *
     * @param atribut Nazev atributu, podle ktereho chcete hledat
     * @param hodnota Hodnota atributu, podle ktere cchete hledat
     * @return seznam typu Seznam vsech najitych Napoju
     */
    Seznam<Napoje> najdiNapojePodleAtributu(String atribut, String hodnota);

    /**
     * Metoda nastavi aktualni ukazatel na atributu seznam na posledni napoj v
     * seznamu
     *
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void nastavPosledni() throws OvladaniException;

    /**
     ** Metoda nastavi aktualni ukazatel na atributu seznam na prvni napoj v
     * seznamu
     *
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void nastavPrvni() throws OvladaniException;

    /**
     * Metoda odebery vsechny Napoje z atributu seznam podle input parametru
     *
     * @param atribut Nazev atributu, podle ktereho chcete odebirat
     * @param hodnota Hodnota atributu, podle ktere cchete odebirat
     */
    void odeberPodleAtributu(String atribut, String hodnota);

    /**
     * Metoda vraci pocet vsech Napoju na atributu seznam
     *
     * @return Pocet Napoju ulozenych na atributu seznam
     */
    int pocet();

    /**
     * Metoda odebere z atributu seznam Napoj na aktualni pozici a vrati jej
     *
     * @return odebrany Napoj
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    Napoje vyjmi() throws OvladaniException;

    /**
     * Metoda vlozi instanci Tridy pivo do atributu seznam na pozici Za aktualni
     * prvek na zakladate inputnutych hodnot Atributu
     *
     * @param seznam hodnot atributu
     * @return Vytvoreny {vlozeny} napoj
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    Napoje vytvorPivoAVlozZaAktualni(Seznam<String> seznam) throws OvladaniException;

    /**
     * Metoda vlozi instanci Tridy limo do atributu seznam na pozici Za aktualni
     * prvek na zakladate inputnutych hodnot Atributu
     *
     * @param seznam hodnot atributu
     * @return Vytvoreny {vlozeny} napoj
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    Napoje vytvorLimooAVlozZaAktualni(Seznam<String> seznam) throws OvladaniException;

    /**
     * Metoda vlozi instanci Tridy vino do atributu seznam na pozici Za aktualni
     * prvek na zakladate inputnutych hodnot Atributu
     *
     * @param seznam hodnot atributu
     * @return Vytvoreny {vlozeny} napoj
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    Napoje vytvorVinoAVlozZaAktualni(Seznam<String> seznam) throws OvladaniException;

    /**
     * Metoda meni atribut seznam, rusi vsechna data v seznamu
     */
    void zrus();

    /**/
    /**
     * Metoda ulozi data atributu spojovy seznam do binarniho souboru
     *
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void zalohuj() throws OvladaniException;

    /**/
    /**
     * Metoda obnovi vsechny Napoje z binarniho souboru (Ve forme objektu)
     * Nastavi atribut seznam na obnovena data tzn, ze se predchozi data v
     * atributu seznam smazou
     *
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void obnov() throws OvladaniException;

    /**/
    /**
     * Metoda ulozi data atributu seznam do textoveho souboru podle nazvu
     * souboru; Pokud soubor neexistuje, tak se vytvori
     *
     * @param nazevSouboru soubor do ktereho chcete ukladat;
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void ulozText(String nazevSouboru) throws OvladaniException;

    /**
     * Metoda obnovi vsechny Napoje z binarniho souboru (Ve forme objektu)
     * Nastavi atribut seznam na obnovena data tzn, ze se predchozi data v
     * atributu seznam smazou
     *
     * @param nazevSouboru nazev souboru, ze ktereho chcete data nacitat
     * @throws cz.upce.fei.boop.pujcovna.spravce.OvladaniException
     */
    void nactiText(String nazevSouboru) throws OvladaniException;

    void clearAktualni();

    Napoje odeberAktualni() throws OvladaniException;

    void setAktualni(Napoje napoj) throws OvladaniException;

    void ulozModifikace();

    void clearModifikace();

    Seznam<Napoje> dejAOdberPosledniModifikaci();

    int sizeModifikaci();

    Stream<Napoje> stream();

    void addAll(Seznam<Napoje> sez);

    Seznam<Napoje> najdiNapojePodleAtributuANastavJeNaSeznam(String atributJmeno, String hodnota);

    void vytvorNapojAVlozMistoAktualni(Seznam<String> sez) throws OvladaniException;

    /**
     * Metoda vraci atribut seznam
     *
     * @return atribut seznam
     */
    Seznam<Napoje> getSeznam();
}
