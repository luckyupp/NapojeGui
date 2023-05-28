/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui.ComboBoxDialog;

/**
 *
 * @author matam
 */
public class FilterDialog extends ComboBoxDialog<String> {

    public FilterDialog() {
        super();
        setTitle("Vyber atribbut");
        super.getDruhComboBox().getItems().addAll("druh", "cena", "objem", "id");

    }
}
