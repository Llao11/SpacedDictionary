package org.PoC;

import javax.swing.*;
import java.util.ArrayList;

class ViewGUI {
    private final Controller controller;
    private MainWindow mainWindow;
    EditDictionaryWindow editDictionaryWindow;
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
        mainWindow.createMainWindow(dictionaries, mainWindowWidth, mainWindowHeight);
    }

    /**
     *
     * @param dictionaries
     *
     */
    public void refreshMainWindow(ArrayList<String> dictionaries){
        mainWindow.showDictionaries(dictionaries);
    }

    public void refreshDictionaryEditWindow(){
        if (editDictionaryWindow!=null){
            editDictionaryWindow.refreshTable();
        }
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

    /**
     * creates new window with request for a new card to add
     */
    public void newCardWindow(String dictionaryName){
        NewCardWindow newCardWindow = new NewCardWindow(controller,dictionaryName);
    }

    /**
     * creates new window to repeat cards
     */
    public void repeatCardWindow(Controller controller, String dictionaryName, ArrayList<Card> cards){
        RepeatWindow repeatWindow = new RepeatWindow(controller,dictionaryName,cards);
    }


    public void informationWindow(String message){
        InfoWindow infoWindow = new InfoWindow(message);
    }

    public void editDictionaryWindow(ArrayList<Card> cards,String dictionary) {
        editDictionaryWindow = new EditDictionaryWindow(controller,cards,dictionary);
    }
}
