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
public class NovyPivo extends NovyDialog {

    private GridPane root;
    private Label labelProcentoAlkoholu = new Label("Procento alkoholu:");
    private TextField textFieldProcentoAlkoholu = new TextField();
    private Label labelPocetStupnu = new Label("Pocet stupnu:");
    private TextField textFieldPocetStupnu = new TextField();

    public NovyPivo() {
        super();
        root = (GridPane) super.getDialogPane().getContent();

        root.add(labelProcentoAlkoholu, 0, 2);
        root.add(textFieldProcentoAlkoholu, 1, 2);
        root.add(labelPocetStupnu, 0, 3);
        root.add(textFieldPocetStupnu, 1, 3);
    }

    public Label getLabelProcentoAlkoholu() {
        return labelProcentoAlkoholu;
    }

    public TextField getTextFieldProcentoAlkoholu() {
        return textFieldProcentoAlkoholu;
    }

    public Label getLabelPocetStupnu() {
        return labelPocetStupnu;
    }

    public TextField getTextFieldPocetStupnu() {
        return textFieldPocetStupnu;
    }

    public void setTextFields(String cena, String objem, String procentAlk, String pocetStupnu) {
        super.setTextFields(cena, objem);
        textFieldPocetStupnu.setText(pocetStupnu);
        textFieldProcentoAlkoholu.setText(procentAlk);
    }

}
