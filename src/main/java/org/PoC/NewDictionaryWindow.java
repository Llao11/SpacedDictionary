package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

class NewDictionaryWindow extends JFrame implements ActionListener {
    private Controller controller;
    private JTextField textFieldNewDictionary;

    public NewDictionaryWindow(Controller controller){
        this.controller = controller;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Submit")) {
            String newDictionaryName = textFieldNewDictionary.getText();
            System.out.println("Button pressed: "+newDictionaryName);
            controller.newDictionary(newDictionaryName);

            // TODO: windows doesnt closes
            this.setVisible(false);
            this.dispose();
        }
    }

    public void createNewDictionaryWindow(){
        JFrame newDictionaryFrame = new JFrame() {};
        newDictionaryFrame.setTitle("New Dictionary");
        JButton button = new JButton("Submit");
        button.addActionListener(this);
        JLabel label = new JLabel("Enter dictionary name:");
        textFieldNewDictionary = new JTextField(16);
        JPanel p = new JPanel();
        p.add(label);
        p.add(textFieldNewDictionary);
        p.add(button);
        newDictionaryFrame.add(p);
        newDictionaryFrame.setSize(300,100);
        newDictionaryFrame.setVisible(true);
    }
}
