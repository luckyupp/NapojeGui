/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui;

/**
 *
 * @author matam
 */
public class AlertError extends javafx.scene.control.Alert {

    public AlertError(String title, String headText, String contextText) {
        super(AlertType.ERROR);
        this.setTitle(title);
        this.setHeaderText(headText);
        this.setContentText(contextText);
        this.showAndWait();
    }

}
