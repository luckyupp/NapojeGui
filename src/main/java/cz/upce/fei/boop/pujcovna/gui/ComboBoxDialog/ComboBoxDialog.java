/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.ComboBoxDialog;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author matam
 */
public abstract class ComboBoxDialog<E> extends Dialog<E> {

    private ComboBox<E> druhComboBox = new ComboBox<>();
    private Label labelVyberDruh = new Label();
    private GridPane gridPane = new GridPane();
    private ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

    public ComboBoxDialog() {

        gridPane.add(labelVyberDruh, 1, 1);
        gridPane.add(druhComboBox, 2, 1);
        getDialogPane().setContent(gridPane);

        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(btnOk, btnCancel);

        setResultConverter(buttonType -> {
            if (buttonType == btnOk) {
                return druhComboBox.getSelectionModel().getSelectedItem();
            }
            return null;
        });
    }

    public ComboBox<E> getDruhComboBox() {
        return druhComboBox;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

}
