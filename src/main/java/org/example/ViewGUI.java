package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ViewGUI extends JFrame implements ActionListener {
    private Controller controller;
    private JPanel panelDictionaries;

    private JTextField textFieldNewDictionary;

    public ViewGUI(Controller controller){
        this.controller = controller;
    }
    public void mainWindow(){
        this.setTitle("SpacedDict");
        this.setSize(800,600);
        this.setLayout(null);

        int deltaX = 50;
        addControls("New dictionary",10,10);
        addControls("Edit dictionary",10,10+deltaX);
        addControls("Show all dictionaries",10,10+2*deltaX);
        addControls("Repeat",10,10+3*deltaX);

        panelDictionaries = new JPanel();
        panelDictionaries.setBackground(Color.gray);
        panelDictionaries.setSize(100,100);
        panelDictionaries.setBounds(new Rectangle(250,10,400,400));
        this.add(panelDictionaries);

        // Code to close the application after closing window by clicking X
        this.addWindowListener(new WindowAdapter()
        {public void windowClosing(WindowEvent e)
        {
            dispose();
            System.exit(0);
        }
        });
        this.setVisible(true);
    }

    /**
     * Add button
     */
    private void addControls(String buttonText, int x, int y){
        JButton buttonNew=new JButton(buttonText);
        buttonNew.setBounds(x,y,200, 30);
        this.add(buttonNew);
        buttonNew.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("New dictionary")) {
            newDictionaryWindow();
            System.out.println("\n Create panel");
        } else if (s.equals("Submit")) {


            // TODO Doesnt work - rewrite!
            System.out.println("Button pressed");
            String newDictionaryName = textFieldNewDictionary.getText();
            controller.newDictionary(newDictionaryName);
        }
    }

    private void newDictionaryWindow(){
        JFrame newDictionaryFrame = new JFrame(){};
        newDictionaryFrame.setTitle("New Dictionary");
        JButton button = new JButton("Submit");
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
