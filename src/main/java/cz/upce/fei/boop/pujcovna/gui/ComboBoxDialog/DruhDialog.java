/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.ComboBoxDialog;

import cz.upce.fei.boop.pujcovna.data.Druh;

/**
 *
 * @author matam
 */
public class DruhDialog extends ComboBoxDialog<Druh> {

    public DruhDialog() {
        super();
        setTitle("Vyber druh");
        super.getDruhComboBox().getItems().addAll(Druh.PIVO, Druh.LIMO, Druh.VINO);

    }
}
