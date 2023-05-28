/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui;

import cz.upce.fei.boop.pujcovna.gui.novydialog.NovyLimo;
import cz.upce.fei.boop.pujcovna.gui.novydialog.NovyVino;
import cz.upce.fei.boop.pujcovna.gui.novydialog.NovyPivo;
import cz.upce.fei.boop.pujcovna.gui.ComboBoxDialog.DruhDialog;
import cz.upce.fei.boop.pujcovna.gui.ComboBoxDialog.FilterDialog;
import cz.upce.fei.boop.pujcovna.data.Druh;
import cz.upce.fei.boop.pujcovna.data.Limo;
import cz.upce.fei.boop.pujcovna.data.Napoje;
import cz.upce.fei.boop.pujcovna.data.Pivo;
import cz.upce.fei.boop.pujcovna.data.Vino;
import cz.upce.fei.boop.pujcovna.gui.hodnotaDialog.HodnotaDialog;
import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import java.util.Objects;
import java.util.Optional;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author matam
 */
public class Editor {
    
    private BorderPane root = new BorderPane();
    private HBoxGUI hbox = new HBoxGUI();
    private VBoxGUI vbox = new VBoxGUI();
    private ListViewGui listV = new ListViewGui();
    
    public Editor() {
        root.setCenter(listV);
        root.setRight(vbox);
        root.setBottom(hbox);
        
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public ListViewGui getListV() {
        return listV;
    }
    
    public HBoxGUI getHbox() {
        return hbox;
    }
    
    public VBoxGUI getVbox() {
        return vbox;
    }
    
    public void clearObsList() {
        listV.getListObs().clear();
    }
    
    public Optional<Druh> novyDruhDialog() {
        DruhDialog dialog = new DruhDialog();
        return dialog.showAndWait();
    }
    
    public Optional<Seznam<String>> novyPivo() {
        NovyPivo dialog = new NovyPivo();
        return dialog.showAndWait();
    }
    
    public Optional<Seznam<String>> novyLimo() {
        NovyLimo dialog = new NovyLimo();
        return dialog.showAndWait();
    }
    
    public Optional<Seznam<String>> novyVino() {
        NovyVino dialog = new NovyVino();
        return dialog.showAndWait();
    }
    
    public Optional<String> vyhodGenerujDialogPocet() {
        HodnotaDialog dialog = new HodnotaDialog("Pocet");
        return dialog.showAndWait();
        
    }
    
    public Optional<String> vyhodHodnotaDialog(String s) {
        HodnotaDialog dialog = new HodnotaDialog(s);
        return dialog.showAndWait();
        
    }
    
    public void pridejVechnyZeSeznamuDoObsListu(Seznam<Napoje> sez) {
        sez.forEach(napoj -> {
            listV.getListObs().add(napoj);
        });
    }
    
    public void nastavAktualni(Napoje napoj) {
        listV.getSelectionModel().select(napoj);
    }
    
    public Optional<Seznam<String>> editPivo(Pivo p) {
        NovyPivo dialog = new NovyPivo();
        dialog.setTextFields(String.valueOf(p.getCena()), String.valueOf(p.getObjem()),
                String.valueOf(p.getProcentoAkolholu()), String.valueOf(p.getPocetStupnu()));
        return dialog.showAndWait();
    }
    
    public Optional<Seznam<String>> editLimo(Limo l) {
        NovyLimo dialog = new NovyLimo();
        dialog.setTextFields(String.valueOf(l.getCena()), String.valueOf(l.getObjem()),
                l.getPrichut());
        return dialog.showAndWait();
    }
    
    public Optional<Seznam<String>> editVino(Vino v) {
        NovyVino dialog = new NovyVino();
        dialog.setText(String.valueOf(v.getCena()), String.valueOf(v.getObjem()),
                v.getBarva(), v.getCukernatost());
        return dialog.showAndWait();
    }
    
    public void clearListVSelection() {
        listV.getSelectionModel().clearSelection();
    }
    
    public String ulozTxt() {
        return FileChooserDialog.ulozSoubor();
    }
    
    public String nactiTxt() {
        return FileChooserDialog.otevritSoubor();
    }
    
    public void nastavPrvni() throws GuiException {
        listVisEmpty();
        listV.getSelectionModel().select(0);
    }
    
    private void listVisEmpty() throws GuiException {
        if (listV.getItems().isEmpty()) {
            throw new GuiException("ListV je prazdny");
        }
    }
    
    public void nastavPosledni() throws GuiException {
        listVisEmpty();
        listV.getSelectionModel().select(listV.getItems().size() - 1);
    }
    
    public void nastavDalsi() throws GuiException {
        listVisEmpty();
        int aktualniIndex = listV.getSelectionModel().getSelectedIndex();
        if (aktualniIndex < listV.getItems().size() - 1) {
            listV.getSelectionModel().select(aktualniIndex + 1);
        }
        
    }
    
    public void vlozDoObsListuZaAktualni(Napoje napoj) throws GuiException {
        if (!listV.getItems().isEmpty()) {
            listV.vlozZaAktualniDoObsListu(napoj);
        } else {
            throw new GuiException("Chyba ukladani do seznamu");
        }
    }
    
    public void alertThrow(String title, String headText, String contextText) {
        AlertError err = new AlertError(title, headText, contextText);
    }
    
    public Optional<String> filterDialog() {
        FilterDialog dialog = new FilterDialog();
        return dialog.showAndWait();
        
    }
    
    public void odeberAktualniListV() {
        listV.getItems().remove(listV.getSelectionModel().getSelectedItem());
    }
    
    public void odeberNapoj(Napoje napoj) {
        listV.getItems().remove(napoj);
    }
    
    public void predchozi() throws GuiException {
        listVisEmpty();
        int aktualniIndex = listV.getSelectionModel().getSelectedIndex();
        if (!Objects.isNull(aktualniIndex) && 0 < aktualniIndex) {
            listV.getSelectionModel().select(aktualniIndex - 1);
        } else {
            throw new GuiException("");
        }
    }
    
    public Napoje dejAktualni() {
        return listV.getSelectionModel().getSelectedItem();
    }
    
}
