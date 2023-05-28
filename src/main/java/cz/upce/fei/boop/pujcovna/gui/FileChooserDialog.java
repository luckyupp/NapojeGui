/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.upce.fei.boop.pujcovna.gui;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author matam
 */
public class FileChooserDialog {

    public static String otevritSoubor() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Otevrit text soubor");
        File selectedFile = chooser.showOpenDialog(new Stage());
        chooser.setInitialFileName("dataAndel");
        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }
        return "";
    }

    public static String ulozSoubor() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Ulozit do souboru");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.setInitialFileName("dataAndel");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);

        File selectedFile = chooser.showSaveDialog(new Stage());

        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }

        return "";
    }

}
