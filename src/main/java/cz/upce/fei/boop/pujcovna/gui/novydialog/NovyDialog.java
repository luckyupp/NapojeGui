/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.novydialog;

import cz.upce.fei.boop.pujcovna.kolekce.Seznam;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;

/**
 *
 * @author matam
 */
public abstract class NovyDialog extends Dialog<Seznam<String>> {

    private GridPane root = new GridPane();
    private Label objemLabel = new Label("Objem:");
    private Label cenaLabel = new Label("Cena:");
    private TextField objemTextField = new TextField();
    private TextField cenaTextField = new TextField();

    public NovyDialog() {

        setTitle("Novy");

        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20, 150, 10, 10));

        root.add(cenaLabel, 0, 0);
        root.add(cenaTextField, 1, 0);
        root.add(objemLabel, 0, 1);
        root.add(objemTextField, 1, 1);

        getDialogPane().setContent(root);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        setResultConverter((ButtonType buttonType) -> {
            if (buttonType == okButtonType) {
                Seznam<String> seznamTxtInputu = new SpojovySeznam<>();
                root.getChildren().forEach((node) -> {
                    if (node instanceof TextField) {
                        seznamTxtInputu.vlozPosledni(((TextInputControl) node).getText());
                    }
                    if (node instanceof ComboBox) {
                        seznamTxtInputu.vlozPosledni(((ComboBox) node).getSelectionModel().getSelectedItem().toString());
                    }
                });
                return seznamTxtInputu;
            }
            return null;
        });
    }

    public GridPane getRoot() {
        return root;
    }

    public Label getObjemLabel() {
        return objemLabel;
    }

    public Label getCenaLabel() {
        return cenaLabel;
    }

    public TextField getObjemTextField() {
        return objemTextField;
    }

    public TextField getCenaTextField() {
        return cenaTextField;
    }

    public void setTextFields(String cena, String objem) {
        cenaTextField.setText(cena);
        objemTextField.setText(objem);
    }

}
