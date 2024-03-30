package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DictView {
    MainWindow mainWindow;
    String textMenu1 = "Choose the action";
    String textChooseAction = "Please enter number:";

    /**
     * Constructor DictView
     */
    public DictView(){
        mainWindow = new MainWindow();
        printIntro();
        mainMenu();
    }

    /**
     *  Create main menu
     */
    public void mainMenu(){
        ArrayList<String> linesMenu = new ArrayList<>();
        linesMenu.add("New dictionary");
        linesMenu.add("Remove dictionary");
        int i = inputMenu(textMenu1,linesMenu);
        System.out.println("You entered: "+i);
    }

    /**
     * Print intro text
     */

    public void printIntro(){
        System.out.println("SpaceDict start");
    }

    /**
     * Main menu
     * @param text - First line text
     * @param lines - list of menu elements
     * @return - chosen element number
     */
    public int inputMenu(String text, ArrayList<String> lines){
        System.out.println(text);
        int i=0;
        for (String item:lines){
            i++;
            System.out.println("\t"+i+". "+item);
        }
        System.out.print(textChooseAction);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(buffReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
