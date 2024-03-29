package org.example;

public class DictController {

    private DictView dictView;
    private Library library;

    public DictController() {
        dictView = new DictView();
        library = new Library();

        //library.printDicts();
        library.newDict("dict3");
    }
}
