package org.PoC;

import javax.swing.*;

class ViewGUI {
    private Controller controller;
    private JPanel panelDictionaries;

    private JTextField textFieldNewDictionary;

    public ViewGUI(Controller controller){
        this.controller = controller;
    }
    public void openMainWindow(){
        MainWindow mainWindow = new MainWindow(controller);
        mainWindow.createMainWindow();
    }
}
