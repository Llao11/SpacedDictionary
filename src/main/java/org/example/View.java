package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class View {
    private Controller controller;
    MainWindow mainWindow;
    String textMenu1 = "Choose the action";
    String textChooseAction = "Please enter number:";
    String textInputDictionary = "Please enter new dictionary name:";

    /**
     * Constructor DictView
     */
    public View(Controller controller){
        this.controller=controller;
        mainWindow = new MainWindow();
    }

    /**
     *  Create main menu
     */
    public int mainMenu(){
        ArrayList<String> linesMenu = new ArrayList<>();
        linesMenu.add("New dictionary");
        linesMenu.add("Remove dictionary");
        linesMenu.add("Show all dictionaries");
        linesMenu.add("Exit");
        int i = inputMenu(textMenu1,linesMenu);
        System.out.println("You entered: "+i);
        return i;
    }

    /**
     * Print intro text
     */
    public void printIntro(){
        System.out.println("SpaceDict start");
    }

    /**
     * Print goodbye text
     */
    public void printOutro(){
        System.out.println("Thank you for using SpaceDict and goodbye!");
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
            int action = Integer.parseInt(buffReader.readLine());
            return  action;
        } catch (IOException e) {
            System.out.println("Wrong input");
            throw new RuntimeException(e);
        }
    }

    public String newDictionary(){
        System.out.print(textInputDictionary);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return  buffReader.readLine();
        } catch (IOException e) {
            System.out.println("Wrong input");
            throw new RuntimeException(e);
        }
    }
}
