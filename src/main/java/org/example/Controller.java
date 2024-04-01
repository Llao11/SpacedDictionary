package org.example;

public class Controller {

    private View view;
    private Library library;

    public Controller() {
        view = new View(this);
        library = new Library(this);
    }

    public int mainCicle(){
        //library.printDicts();
        //library.newDict("Dict3");

        view.printIntro();
        int aciton = view.mainMenu();
        if (aciton == 1){
            String dictionaryName = view.newDictionary();
            library.newDict(dictionaryName);
        } else if (aciton == 2) {

        } else if (aciton == 3) {
            library.printDictionaries();
        } else if (aciton == 4) {
            view.printOutro();
            return 0;
        }
        return 1;
    }


}
