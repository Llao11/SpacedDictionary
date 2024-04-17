package org.PoC;

import javax.swing.*;
import java.awt.*;
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
    private Card currentCard;
    private static final Font wordFont = new Font("Arial", Font.PLAIN, 32);


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
        wordsPanel = new JPanel();
        repeatLogic();
        this.add(mainRepeatPanel);
        mainRepeatPanel.add(wordsPanel);
        mainRepeatPanel.add(controlPanel);
        this.setSize(400,150);
        this.setVisible(true);
    }


    //TODO: replace with spaced repetition logic
    private void repeatLogic(){
        Random random = new Random();
        int randomInt = random.nextInt(cards.size());
        showCardQuestion(cards.get(randomInt));
    }

    private void showCardQuestion(Card card){
        currentCard = card;
        wordsPanel.setVisible(false);
        wordsPanel.removeAll();
        JLabel label1 = new JLabel(card.getWords().get(0));
        label1.setVerticalTextPosition(SwingConstants.CENTER);
        label1.setFont(wordFont);
        wordsPanel.add(label1);
        wordsPanel.setVisible(true);

        controlPanel.removeAll();
        JButton button1 = new JButton("Bad");
        JButton button2 = new JButton("Middle");
        JButton button3 = new JButton("Good");
        button1.setBackground(Color.red);
        button2.setBackground(Color.yellow);
        button3.setBackground(Color.green);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        controlPanel.add(button1);
        controlPanel.add(button2);
        controlPanel.add(button3);
    }

    private void showCardAnswer(Card card){
        wordsPanel.setVisible(false);
        wordsPanel.removeAll();
        JLabel label1 = new JLabel(card.getWords().get(0)+"   - ");
        label1.setVerticalTextPosition(SwingConstants.CENTER);
        label1.setFont(wordFont);
        wordsPanel.add(label1);
        JLabel label2 = new JLabel(card.getWords().get(1));
        label2.setFont(wordFont);
        label2.setFont(wordFont);
        wordsPanel.add(label2);
        wordsPanel.setVisible(true);

        controlPanel.setVisible(false);
        controlPanel.removeAll();
        JButton button = new JButton("Next");
        button.setBackground(Color.cyan);
        button.addActionListener(this);
        controlPanel.add(button);
        controlPanel.setVisible(true);
    }


    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */

    // TODO Add changing in word memorization for card
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        int remembered;
        switch (s) {
            case "Bad":
                remembered = 1;
                controller.updateCard(currentCard, remembered);
                showCardAnswer(currentCard);
                break;
            case "Middle":
                remembered = 2;
                controller.updateCard(currentCard, remembered);
                showCardAnswer(currentCard);
                break;
            case "Good":
                remembered = 3;
                controller.updateCard(currentCard, remembered);
                repeatLogic();
                break;
            case "Next":
                repeatLogic();
                break;
        }
    }
}
