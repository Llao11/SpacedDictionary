package org.PoC;

import javax.swing.*;
import java.util.ArrayList;

class ViewGUI {
    private final Controller controller;
    MainWindow mainWindow;
    NewDictionaryWindow newDictionaryWindow;

    public ViewGUI(Controller controller){
        this.controller = controller;
    }

    public void openMainWindow(ArrayList<String> dictionaries){
        mainWindow = new MainWindow(controller);
        mainWindow.createMainWindow(dictionaries);
    }

    /**
     * creates new window with request for a new dictionary name
     */
    public void newDictionaryWindow(){
        newDictionaryWindow = new NewDictionaryWindow(controller);
        newDictionaryWindow.createNewDictionaryWindow();
    }
}
