package org.PoC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ViewConsole {
    private final String textMenu1 = "Choose the action";
    private final String textChooseAction = "Please enter number: ";
    private final String textInputDictionary = "Please enter dictionary name: ";

    /**
     * Constructor DictView
     */
    public ViewConsole(){
    }

    /**
     *  Create main menu
     */
    public int mainMenu(){
        ArrayList<String> linesMenu = new ArrayList<>();
        linesMenu.add(0,"New dictionary");
        linesMenu.add(1,"Edit dictionary");
        linesMenu.add(2,"Show all dictionaries");
        linesMenu.add(3,"Repeat");
        linesMenu.add(4,"Exit");
        int i = inputMenu(textMenu1,linesMenu);
        System.out.println("You entered: "+i);
        return i;
    }

    /**
     *  Create edit dictionary menu
     */
    public int editDictionaryMenu(){
        ArrayList<String> linesMenu = new ArrayList<>();
        linesMenu.add(0,"Add card");
        linesMenu.add(1,"Remove card");
        linesMenu.add(2,"Rename dictionary");
        linesMenu.add(3,"Remove dictionary");
        linesMenu.add(4,"Back");
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
    public void printWrongInput(){
        System.out.println("Wrong input!");
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


    /**
     * Input dictionary name from console
     * @return dictionary name
     */
    public String enterDictionary(){
        System.out.print(textInputDictionary);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return  buffReader.readLine();
        } catch (IOException e) {
            System.out.println("Wrong input");
            throw new RuntimeException(e);
        }
    }


    /**
     * Input word from console
     * @return dictionary name
     */
    public String enterWord(String text){
        System.out.print(text);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return  buffReader.readLine();
        } catch (IOException e) {
            System.out.println("Wrong input");
            throw new RuntimeException(e);
        }
    }

}
