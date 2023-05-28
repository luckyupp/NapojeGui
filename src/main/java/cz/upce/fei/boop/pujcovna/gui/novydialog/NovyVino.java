/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.novydialog;

import cz.upce.fei.boop.pujcovna.data.BarvaVina;
import cz.upce.fei.boop.pujcovna.data.Cukernatost;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author matam
 */
public class NovyVino extends NovyDialog {

    private GridPane root;
    private Label labelCukernatost = new Label("Cukernatost");
    private ComboBox<Cukernatost> comboCukernatost = new ComboBox<>();
    private Label labelBarvaVina = new Label("Barva vina");
    private ComboBox<BarvaVina> comboBarvat = new ComboBox<>();

    public NovyVino() {
        super();
        root = (GridPane) super.getDialogPane().getContent();

        comboCukernatost.getItems().addAll(Cukernatost.POLOSLADKE, Cukernatost.POLOSUCHE,
                Cukernatost.SLADKE, Cukernatost.SUCHE);

        comboBarvat.getItems().addAll(BarvaVina.BILE, BarvaVina.CERVENE, BarvaVina.RUZOVE);

        root.add(labelCukernatost, 0, 2);
        root.add(comboCukernatost, 1, 2);
        root.add(labelBarvaVina, 0, 3);
        root.add(comboBarvat, 1, 3);
    }

    public Label getLabelCukernatost() {
        return labelCukernatost;
    }

    public ComboBox<Cukernatost> getComboCukernatost() {
        return comboCukernatost;
    }

    public Label getLabelBarvaVina() {
        return labelBarvaVina;
    }

    public ComboBox<BarvaVina> getComboBarvat() {
        return comboBarvat;
    }

    public void setText(String cena, String objem, BarvaVina barva, Cukernatost cukernatost) {
        super.setTextFields(cena, objem);
        comboBarvat.getSelectionModel().select(barva);
        comboCukernatost.getSelectionModel().select(cukernatost);
    }
}
