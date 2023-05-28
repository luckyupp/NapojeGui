/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author matam
 */
public class VBoxGUI extends VBox {

    private final Button btnPrvni = new Button("Prvni");
    private final Button btnDalsi = new Button("Dalsi");
    private final Button btnPredchozi = new Button("Predchozi");
    private final Button btnPosledni = new Button("Posledni");
    private final Button btnEdituj = new Button("Edituj");
    private final Button btnVyjmi = new Button("Vyjmi");
    private final Button btnClear = new Button("Clear");

    public VBoxGUI() {
        super();
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(btnPrvni, btnDalsi, btnPredchozi, btnPosledni,
                btnEdituj, btnVyjmi, btnClear);

    }

}
