/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.novydialog;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author matam
 */
public class NovyLimo extends NovyDialog {

    private GridPane root;
    private Label labelPrichut = new Label("Prichut:");
    private TextField textPrichut = new TextField();

    public NovyLimo() {
        super();

        root = (GridPane) super.getDialogPane().getContent();
        root.add(labelPrichut, 0, 2);
        root.add(textPrichut, 1, 2);
    }

    public Label getLabelPrichut() {
        return labelPrichut;
    }

    public TextField getTextPrichut() {
        return textPrichut;
    }

    public void setTextFields(String cena, String objem, String prichut) {
        super.setTextFields(cena, objem);
        textPrichut.setText(prichut);
    }

}
