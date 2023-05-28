/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.hodnotaDialog;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author matam
 */
public class HodnotaDialog extends Dialog<String> {

    private TextField txtField = new TextField();
    private Label labelHodnota = new Label();

    public HodnotaDialog(String hodnotaLabel) {

        this.setTitle(hodnotaLabel);
        labelHodnota.setText("Zadej " + hodnotaLabel);

        GridPane gridPane = new GridPane();
        gridPane.add(labelHodnota, 1, 1);
        gridPane.add(txtField, 2, 1);
        getDialogPane().setContent(gridPane);

        ButtonType btnOk = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(btnOk, btnCancel);

        setResultConverter(buttonType -> {
            if (buttonType == btnOk) {
                if (!txtField.getText().isEmpty()) {
                    return txtField.getText();
                }
            }
            return null;
        });
    }
}
