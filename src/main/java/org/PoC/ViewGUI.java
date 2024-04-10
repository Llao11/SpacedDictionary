package org.PoC;

import javax.swing.*;
import java.util.ArrayList;

class ViewGUI {
    private final Controller controller;
    private MainWindow mainWindow;
    private NewDictionaryWindow newDictionaryWindow;
    private final int mainWindowWidth = 800;
    private final int mainWindowHeight = 600;

    public ViewGUI(Controller controller){
        this.controller = controller;
    }

    /**
     *
     * @param dictionaries
     */
    public void openMainWindow(ArrayList<String> dictionaries){
        mainWindow = new MainWindow(controller);
        mainWindow.createMainWindow(dictionaries, mainWindowWidth,mainWindowHeight);
    }

    /**
     *
     * @param dictionaries
     */
    public void refreshMainWindow(ArrayList<String> dictionaries){
        mainWindow.setVisible(false);
        int width = mainWindow.getWidth();
        int height =  mainWindow.getHeight();
        mainWindow.dispose();
        mainWindow = new MainWindow(controller);
        mainWindow.createMainWindow(dictionaries,width,height );
    }

    public void enterEditMode(){
        mainWindow.showEditControlPanel();
    }

    public void exitEditMode(){
        mainWindow.showMainControlPanel();
    }

    /**
     * creates new window with request for a new dictionary name
     */
    public void newDictionaryWindow(){
        newDictionaryWindow = new NewDictionaryWindow(controller);
        newDictionaryWindow.createNewDictionaryWindow();
    }
}
