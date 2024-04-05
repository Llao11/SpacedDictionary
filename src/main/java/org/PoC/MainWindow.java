package org.PoC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainWindow extends JFrame implements ActionListener {
    private Controller controller;
    private JPanel panelDictionaries;

    private JTextField textFieldNewDictionary;

    public MainWindow(Controller controller){
        this.controller = controller;
    }
    public void createMainWindow(){
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
            controller.newDictionaryWindow();
        } else if (s.equals("Submit")) {

        }
    }

}
