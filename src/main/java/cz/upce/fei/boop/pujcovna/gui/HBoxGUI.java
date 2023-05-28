/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author matam
 */
public class HBoxGUI extends HBox {

    private final Button btnGeneruj = new Button("Generuj");
    private final Button btnUloz = new Button("Uloz");
    private final Button btnNacti = new Button("Nacti");
    private final Button btnNovy = new Button("Novy");
    private final Button btnFilter = new Button("Filter");
    private final Button btnNajdi = new Button("Najdi");
    private final Button btnZalohuj = new Button("Zalohuj");
    private final Button btnObnov = new Button("Obnov");
    private final Button btnZrus = new Button("Zrus");
    private final Button btnRevertFilter = new Button("RevertFilter");

    public HBoxGUI() {
        super();
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(btnGeneruj, btnNacti, btnUloz, btnNovy, btnFilter,
                btnZalohuj, btnNajdi, btnObnov, btnZrus, btnRevertFilter);
    }

    public Button getBtnRevertFilter() {
        return btnRevertFilter;
    }

}
