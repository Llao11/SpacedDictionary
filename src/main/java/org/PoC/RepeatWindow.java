package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class RepeatWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private final String dictionaryName;
    private ArrayList<Card> cards;
    private final JPanel controlPanel;
    private final JPanel mainRepeatPanel;
    private final JPanel wordsPanel;


    /**
     * Method to create new window with request of the new dictionary name
     */
    public RepeatWindow(Controller controller, String dictionaryName, ArrayList<Card> cards){
        this.controller = controller;
        this.dictionaryName=dictionaryName;
        this.cards = cards;
        this.setTitle("Dictionary: "+dictionaryName);

        mainRepeatPanel = new JPanel();
        mainRepeatPanel.setLayout(new BoxLayout(mainRepeatPanel,BoxLayout.Y_AXIS));

        controlPanel = new JPanel();
        JButton button = new JButton("Next");
        button.addActionListener(this);
        controlPanel.add(button);

        wordsPanel = new JPanel();

        repeatLogic();


        this.add(mainRepeatPanel);
        mainRepeatPanel.add(wordsPanel);
        mainRepeatPanel.add(controlPanel);
        this.setSize(400,200);
        this.setVisible(true);
    }

    private void repeatCard(Card card){
        wordsPanel.setVisible(false);
        wordsPanel.removeAll();
        JLabel label1 = new JLabel(card.getWords().get(0));
        JLabel label2 = new JLabel(card.getWords().get(1));
        wordsPanel.add(label1);
        wordsPanel.add(label2);
        wordsPanel.setVisible(true);
    }

    private void repeatLogic(){
        //TODO: replace with spaced repetition logic
        Random random = new Random();
        int randomInt = random.nextInt(cards.size());
        repeatCard(cards.get(randomInt));
    }



    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Next")) {
            repeatLogic();
        }
    }


}
