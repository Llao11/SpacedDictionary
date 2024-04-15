package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class RepeatWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private final String dictionaryName;
    private ArrayList<Card> cards;
    private final JPanel controlPanel;
    private final JPanel wordsPanel;


    /**
     * Method to create new window with request of the new dictionary name
     */
    public RepeatWindow(Controller controller, String dictionaryName, ArrayList<Card> cards){
        this.controller = controller;
        this.dictionaryName=dictionaryName;
        this.cards = cards;
        this.setTitle("Dictionary: "+dictionaryName);
        controlPanel = new JPanel();
        wordsPanel = new JPanel();

        JButton button = new JButton("Next");
        button.addActionListener(this);

        // TODO add logic to show cards


        //wordsPanel.setLayout(new BoxLayout());

        this.pack();
        this.add(controlPanel);
        this.setSize(400,300);
        this.setVisible(true);
    }

    private void nextCard(Card card){
        JLabel label1 = new JLabel("Enter word 1:");
        JLabel label2 = new JLabel("Enter word 2:");
        JPanel panel = new JPanel();
    }



    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Submit")) {
            this.setVisible(false);
            this.dispose();
        }
    }


}
