/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui;

import cz.upce.fei.boop.pujcovna.data.Napoje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author matam
 */
public class ListViewGui extends ListView<Napoje> {

    private ObservableList<Napoje> listObs = FXCollections.observableArrayList();

    public ListViewGui() {
        super();
        this.setItems(listObs);
    }

    public ObservableList<Napoje> getListObs() {
        return listObs;
    }

    public void vlozZaAktualniDoObsListu(Napoje napoj) {
        int index = this.getSelectionModel().getSelectedIndex() + 1;
        if (index <= this.getItems().size()) {
            listObs.add(index, napoj);
        }
    }

}
