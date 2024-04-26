package org.PoC;

import java.util.ArrayList;

public class Controller {

    private final ViewConsole viewConsole;
    private final ViewGUI viewGUI;
    private final Library library;
    private boolean isEditMode;

    public Controller() {
        viewConsole = new ViewConsole();
        viewGUI = new ViewGUI(this);
        library = new Library();
        isEditMode=false;
    }

    /**
     * Start new GUI JFrame as main window
     */
    public void openMainWindow(){
        ArrayList<String> dictionaries =library.getDictionaries();
        viewGUI.openMainWindow(dictionaries);
    }

    /**
     * Start new GUI JFrame as main window
     */
    public void repeatDictionary(String dictionary){
        ArrayList<Card> cards = library.getDictionaryCards(dictionary);
        if (cards.isEmpty()){
            viewGUI.informationWindow("The dictionary is empty");
        }else{
            viewGUI.repeatCardWindow(this,dictionary,cards);
        }
    }


    /**
     * creates new window with request for a new dictionary name
     */
    public void newDictionaryWindow(){
        viewGUI.newDictionaryWindow();
    }

    /**
     * creates new table in DB and refreshes the main window
     * @param dictionaryName String name of the new dictionary
     */
    public void newDictionary(String dictionaryName){
        library.newDictionary(dictionaryName);
        refreshMainWindow();
    }

    public void removeDictionary(String dictionaryName){
        library.removeDictionary(dictionaryName);
        refreshMainWindow();
    }

    public void refreshMainWindow(){
        ArrayList<String> dictionaries = library.getDictionaries();
        viewGUI.refreshMainWindow(dictionaries);
    }

    public void refreshDictionaryEditWindow(){
        viewGUI.refreshDictionaryEditWindow();
    }

    public void addCardWindow(String dictionaryName){
        viewGUI.newCardWindow(dictionaryName);
    }

    public void addCard(String dictionaryName,String word1, String word2){
        library.addCard(word1,word2,dictionaryName);
    }

    public void enterEditMode(){
        isEditMode = true;
        viewGUI.enterEditMode();
    }
    public void exitEditMode(){
        isEditMode = false;
        viewGUI.exitEditMode();
    }

    public boolean isEditMode() {
        return isEditMode;
    }

    public void updateCard(Card card,int remembered ){
        //TODO: add logic to update values of the card based on remembered value: bad=1, middle=2, good=3
    }

    public void createEditDictionaryWindow(String dictionary) {
        ArrayList<Card> cards = library.getDictionaryCards(dictionary);
        viewGUI.editDictionaryWindow(cards,dictionary);
    }



    /**
     * Start console respond cycle
     * @return int 1 or 0 to
     */
    public int mainConsoleCycle(){
        viewConsole.printIntro();
        int action = viewConsole.mainMenu();
        if (action == 5){        // Exit
            viewConsole.printOutro();
            return 0;       // exit code
        } else if (action == 1){        // New dictionary
            String dictionaryName = viewConsole.enterDictionary();
            library.newDictionary(dictionaryName);
        } else if (action == 2) {       // Edit dictionary
            String dictionaryName = viewConsole.enterDictionary();
            int action1 = viewConsole.editDictionaryMenu();
            if (action1 == 1){              // Add card
                String word1 = viewConsole.enterWord("Please enter a first word: ");
                String word2 = viewConsole.enterWord("Please enter a second word: ");
                library.addCard(word1, word2, dictionaryName);
            } else if (action1 == 2) {       // Remove card

            } else if (action1 == 3) {       // Rename dictionary

            } else if (action1 == 4) {       // Remove dictionary
                library.removeDictionary(dictionaryName);
            } else if (action1 == 5) {       // Back

            } else {
                viewConsole.printWrongInput();
            }
        } else if (action == 3) {       // Show all dictionaries
            library.printDictionaries();
        } else if (action == 4) {       // Repeat
            String dictionaryName = viewConsole.enterDictionary();
            library.getDictionaryCards(dictionaryName);
        } else {
            viewConsole.printWrongInput();
        }
        return 1;           // repeat code
    }

}
