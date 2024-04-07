package org.PoC;

public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.openMainWindow();
        int result=1;
        while (result !=0) {
            result =controller.mainConsoleCycle();

        }
    }
}