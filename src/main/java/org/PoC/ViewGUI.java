package org.PoC;

import javax.swing.*;
import java.util.ArrayList;

class ViewGUI {
    private final Controller controller;

    public ViewGUI(Controller controller){
        this.controller = controller;
    }
    public void openMainWindow(ArrayList<String> dictionaries){
        MainWindow mainWindow = new MainWindow(controller);
        mainWindow.createMainWindow(dictionaries);
    }
}
