package org.example;

public class Controller {

    private ViewConsole view;
    private Library library;

    public Controller() {
        view = new ViewConsole();
        library = new Library();
    }

    public int mainCycle(){
        view.printIntro();
        int action = view.mainMenu();
        if (action == 5){        // Exit
            view.printOutro();
            return 0;       // exit code
        } else if (action == 1){        // New dictionary
            String dictionaryName = view.enterDictionary();
            library.newDictionary(dictionaryName);
        } else if (action == 2) {       // Edit dictionary
            String dictionaryName = view.enterDictionary();
            int action1 = view.editDictionaryMenu();
            if (action1 == 1){              // Add card
                String word1 = view.enterWord("Please enter a first word: ");
                String word2 = view.enterWord("Please enter a second word: ");
                library.addCard(word1, word2, dictionaryName);
            } else if (action1 == 2) {       // Remove card

            } else if (action1 == 3) {       // Rename dictionary

            } else if (action1 == 4) {       // Remove dictionary
                library.removeDictionariy(dictionaryName);
            } else if (action1 == 5) {       // Back

            } else {
                view.printWrongInput();
            }
        } else if (action == 3) {       // Show all dictionaries
            library.printDictionaries();
        } else if (action == 4) {       // Repeat
            String dictionaryName = view.enterDictionary();
            library.getDictionary(dictionaryName);
        } else {
            view.printWrongInput();
        }
        return 1;           // repeat code
    }
}
