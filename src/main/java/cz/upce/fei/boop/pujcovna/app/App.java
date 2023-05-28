/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.app;

import cz.upce.fei.boop.pujcovna.data.Druh;
import cz.upce.fei.boop.pujcovna.data.Limo;
import cz.upce.fei.boop.pujcovna.data.Napoje;
import cz.upce.fei.boop.pujcovna.data.Pivo;
import cz.upce.fei.boop.pujcovna.data.Vino;
import cz.upce.fei.boop.pujcovna.gui.Editor;
import cz.upce.fei.boop.pujcovna.gui.GuiException;
import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import cz.upce.fei.boop.pujcovna.spravce.FasadaImpmelemetace;
import cz.upce.fei.boop.pujcovna.spravce.Ovladani;
import cz.upce.fei.boop.pujcovna.spravce.OvladaniException;
import java.util.Optional;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 *
 * @author matam
 */
public class App extends Application implements EventHandler<ActionEvent> {

    private final Editor editor = new Editor();
    private final Ovladani ovladani = new FasadaImpmelemetace();
    private IntegerProperty obsHodnota = new SimpleIntegerProperty(0);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Button btnRevert = editor.getHbox().getBtnRevertFilter();
        btnRevert.setVisible(false);
        obsHodnota.addListener((observable, staraValue, novaValue) -> {
            btnRevert.setVisible(novaValue.intValue() > 0);
        });

        editor.getHbox().getChildren().forEach(btn -> {
            if (btn instanceof Button but) {
                but.setOnAction(this);
            }
        });

        editor.getVbox().getChildren().forEach(btn -> {
            if (btn instanceof Button but) {
                but.setOnAction(this);
            }
        });

        editor.getListV().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                edit();
            }
            if (e.getButton() == MouseButton.PRIMARY) {
                try {
                    ovladani.setAktualni(editor.getListV().getSelectionModel().getSelectedItem());
                } catch (OvladaniException ex) {
                }
            }
        });

        Scene scene = new Scene(editor.getRoot(), 700, 700);
        stage.setScene(scene);
        stage.show();
    }

    @Override

    public void handle(ActionEvent t) {
        Object source = t.getSource();
        if (source instanceof Button) {
            if (editor.getListV().getSelectionModel().getSelectionMode().equals(SelectionMode.MULTIPLE)) {
                nastavSelectionModelNaSingle();
            }
            try {
                if (!editor.getListV().getSelectionModel().getSelectedItem().equals(ovladani.dej())) {
                    editor.nastavAktualni(ovladani.dej());
                }
            } catch (OvladaniException ex) {
                editor.getListV().getSelectionModel().clearSelection();
            } catch (NullPointerException ex) {
            }
            switch (((Labeled) source).getText()) {

                case "Generuj" -> {

                    Optional<String> vyhodGenerujDialogPocet = editor.vyhodGenerujDialogPocet();
                    if (vyhodGenerujDialogPocet.isPresent()) {
                        try {
                            int pocet = Integer.parseInt(vyhodGenerujDialogPocet.get());
                            editor.clearObsList();
                            ovladani.generuj(pocet);
                            editor.pridejVechnyZeSeznamuDoObsListu(ovladani.getSeznam());
                        } catch (NumberFormatException ex) {
                            editor.alertThrow("Chyba", "Chyba formatu", "Zadany agrument neni cislo");
                        }
                    }

                }
                case "Uloz" -> {
                    try {
                        String path = editor.ulozTxt();
                        if (!path.isEmpty()) {
                            ovladani.ulozText(path);
                        }
                    } catch (OvladaniException ex) {
                        editor.alertThrow("Chyba", "Chyba ukladani", "Seznam nebyl ulozen");
                    }
                }
                case "Nacti" -> {
                    try {
                        String path = editor.nactiTxt();
                        if (!path.isEmpty()) {
                            ovladani.nactiText(path);
                            editor.clearObsList();
                            editor.pridejVechnyZeSeznamuDoObsListu(ovladani.getSeznam());
                        }
                    } catch (OvladaniException ex) {
                        editor.alertThrow("Chyba", "Chyba nacitani", "Seznam nebyl nacten");
                    }
                }
                case "Zrus" -> {
                    ovladani.zrus();
                    editor.clearObsList();
                }
                case "Zalohuj" -> {
                    try {
                        ovladani.zalohuj();
                    } catch (OvladaniException ex) {
                        editor.alertThrow("Chyba", "Chyba zalohovani", "Seznam nebyl zalohovan");
                    }
                }
                case "Obnov" -> {
                    try {
                        ovladani.obnov();
                    } catch (OvladaniException ex) {
                        editor.alertThrow("Chyba", "Chyba obnovovani", "Seznam nebyl obnoven");
                    }
                    editor.clearObsList();
                    editor.pridejVechnyZeSeznamuDoObsListu(ovladani.getSeznam());
                }
                case "Novy" -> {
                    Optional<Druh> opt = editor.novyDruhDialog();
                    if (opt.isPresent()) {
                        try {
                            switch (opt.get().toString()) {
                                case "PIVO" -> {
                                    Optional<Seznam<String>> optional = editor.novyPivo();
                                    if (optional.isPresent()) {
                                        editor.vlozDoObsListuZaAktualni(
                                                ovladani.vytvorPivoAVlozZaAktualni(optional.get()));
                                    }
                                }
                                case "LIMO" -> {
                                    Optional<Seznam<String>> optional = editor.novyLimo();
                                    if (optional.isPresent()) {
                                        editor.vlozDoObsListuZaAktualni(
                                                ovladani.vytvorLimooAVlozZaAktualni(optional.get()));
                                    }
                                }
                                case "VINO" -> {
                                    Optional<Seznam<String>> optional = editor.novyVino();
                                    if (optional.isPresent()) {
                                        editor.vlozDoObsListuZaAktualni(
                                                ovladani.vytvorVinoAVlozZaAktualni(optional.get()));
                                    }
                                }
                            }

                        } catch (OvladaniException | GuiException ex) {
                            editor.alertThrow("Chyba", "Chyba ukladani", "Neni nastaven aktualni");
                        } catch (NumberFormatException ex) {
                            editor.alertThrow("Chyba", "Chyba zadavani", "Spatny format cisel");
                        }
                    }
                }

                case "Filter" -> {
                    Optional<String> opt = editor.filterDialog();
                    if (opt.isPresent()) {
                        Optional<String> hodnotaOpt = editor.vyhodHodnotaDialog(opt.get());
                        if (hodnotaOpt.isPresent()) {
                            obsHodnota.set(obsHodnota.get() + 1);
                            ovladani.ulozModifikace();
                            editor.clearObsList();
                            ovladani.najdiNapojePodleAtributuANastavJeNaSeznam(opt.get(), hodnotaOpt.get());
                            editor.pridejVechnyZeSeznamuDoObsListu(ovladani.getSeznam());
                        }
                    }
                }
                case "RevertFilter" -> {
                    ovladani.zrus();
                    editor.clearObsList();
                    ovladani.addAll(ovladani.dejAOdberPosledniModifikaci());
                    editor.pridejVechnyZeSeznamuDoObsListu(ovladani.getSeznam());
                    obsHodnota.set(obsHodnota.get() - 1);
                }
                case "Najdi" -> {
                    editor.getListV().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    editor.clearListVSelection();
                    ovladani.clearAktualni();
                    Optional<String> opt = editor.filterDialog();
                    if (opt.isPresent()) {
                        Optional<String> hodnotaOpt = editor.vyhodHodnotaDialog(opt.get());
                        if (hodnotaOpt.isPresent()) {
                            editor.getListV().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                            ovladani.najdiNapojePodleAtributu(opt.get(), hodnotaOpt.get()).forEach(napoj -> {
                                if (editor.getListV().getItems().contains(napoj)) {
                                    editor.getListV().getSelectionModel().select(napoj);
                                }
                            });
                        }
                    }
                }
                case "Clear" -> {
                    editor.clearListVSelection();
                    ovladani.clearAktualni();
                }
                case "Posledni" -> {
                    try {
                        ovladani.nastavPosledni();
                        editor.nastavPosledni();
                    } catch (OvladaniException | GuiException ex) {
                        editor.alertThrow("Chyba", "Chyba posunu", "Seznam je prazdny");
                    }
                }

                case "Prvni" -> {
                    try {
                        ovladani.nastavPrvni();
                        editor.nastavPrvni();
                    } catch (OvladaniException | GuiException ex) {
                        editor.alertThrow("Chyba", "Chyba posunu", "Seznam je prazdny");
                    }
                }
                case "Dalsi" -> {
                    try {
                        ovladani.dalsi();
                        editor.nastavDalsi();
                    } catch (OvladaniException | GuiException ex) {
                        editor.alertThrow("Chyba", "Chyba posunu", "Seznam je prazdny, nebo jiz neni dalsi prvek "
                                + "nebo neni nastaven aktualni");
                    }

                }

                case "Edituj" -> {
                    edit();
                }
                case "Vyjmi" -> {
                    try {
                        editor.odeberNapoj(ovladani.vyjmi());
                        editor.clearListVSelection();
                    } catch (OvladaniException ex) {
                        editor.alertThrow("Chyba", "Chyba aktualni", "Aktualni neni nastaven");
                    }
                }
                case "Predchozi" -> {
                    try {
                        editor.predchozi();
                        ovladani.setAktualni(editor.dejAktualni());
                    } catch (GuiException | OvladaniException ex) {
                        editor.alertThrow("Chyba", "Chyba posunu", "Seznam je prazdny, nebo neni predchozi prvek, nebo je aktualni null");
                    }
                }
            }
        }

    }

    private void nastavSelectionModelNaSingle() {
        editor.getListV().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void edit() {
        if (!editor.getListV().getItems().isEmpty() && editor.getListV().getSelectionModel().getSelectedItem() != null) {
            Napoje nap = editor.getListV().getSelectionModel().getSelectedItem();
            Optional<Seznam<String>> opt;
            switch (nap.getDruh()) {
                case PIVO -> {
                    opt = editor.editPivo((Pivo) nap);
                }
                case VINO -> {
                    opt = editor.editVino((Vino) nap);
                }
                case LIMO -> {
                    opt = editor.editLimo((Limo) nap);
                }
                default ->
                    throw new AssertionError();
            }
            if (!opt.isEmpty()) {
                if (opt.isPresent()) {
                    try {
                        switch (nap.getDruh()) {
                            case PIVO -> {
                                ovladani.setAktualni(nap);
                                editor.vlozDoObsListuZaAktualni(ovladani.vytvorPivoAVlozZaAktualni(opt.get()));
                                editor.odeberNapoj(ovladani.odeberAktualni());

                            }
                            case VINO -> {
                                ovladani.setAktualni(nap);
                                editor.vlozDoObsListuZaAktualni(ovladani.vytvorVinoAVlozZaAktualni(opt.get()));
                                editor.odeberNapoj(ovladani.odeberAktualni());

                            }
                            case LIMO -> {
                                ovladani.setAktualni(nap);
                                editor.vlozDoObsListuZaAktualni(ovladani.vytvorLimooAVlozZaAktualni(opt.get()));
                                editor.odeberNapoj(ovladani.odeberAktualni());

                            }
                            default ->
                                throw new AssertionError();
                        }
                    } catch (OvladaniException | GuiException ex) {
                    } catch (NumberFormatException ex) {
                        editor.alertThrow("Chyba", "Chyba formatu", "Zadany agrument neni cislo");
                    }
                    editor.clearListVSelection();
                }
            }
        }
    }
}
