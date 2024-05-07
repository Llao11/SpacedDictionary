package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RenameDictionaryWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private JTextField textFieldNewDictionary;
    private String oldDictionaryName;

    public RenameDictionaryWindow(Controller controller){
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
            controller.renameDictionary(oldDictionaryName,newDictionaryName);
            this.setVisible(false);
            this.dispose();
        }
    }

    /**
     * Method to create new window with request of the new dictionary name
     */
    public void createRenameDictionaryWindow(String oldDictionaryName){
        this.oldDictionaryName = oldDictionaryName;
        this.setTitle("Rename Dictionary "+oldDictionaryName);
        JButton button = new JButton("Submit");
        button.addActionListener(this);
        JLabel label = new JLabel("Enter new dictionary name:");
        textFieldNewDictionary = new JTextField(16);
        JPanel p = new JPanel();
        p.add(label);
        p.add(textFieldNewDictionary);
        p.add(button);
        this.add(p);
        this.setSize(500,100);
        this.setVisible(true);
    }
}
