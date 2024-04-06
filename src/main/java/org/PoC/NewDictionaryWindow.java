package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

class NewDictionaryWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private JTextField textFieldNewDictionary;

    public NewDictionaryWindow(Controller controller){
        this.controller = controller;
    }

    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Submit")) {
            String newDictionaryName = textFieldNewDictionary.getText();
            System.out.println("Button pressed: "+newDictionaryName);
            controller.newDictionary(newDictionaryName);
            this.setVisible(false);
            this.dispose();
        }
    }

    /**
     * Method to create new window with request of the new dictionary name
     */
    public void createNewDictionaryWindow(){
        this.setTitle("New Dictionary");
        JButton button = new JButton("Submit");
        button.addActionListener(this);
        JLabel label = new JLabel("Enter dictionary name:");
        textFieldNewDictionary = new JTextField(16);
        JPanel p = new JPanel();
        p.add(label);
        p.add(textFieldNewDictionary);
        p.add(button);
        this.add(p);
        this.setSize(300,100);
        this.setVisible(true);
    }
}
