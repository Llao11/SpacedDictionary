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
    private final String buttonBadText = "<html><center>"  + "<font size=\"5\">Bad </font> <br>"+"show answer" + "</center></html>";
    private final String buttonMiddleText ="<html><center>"+ "<font size=\"5\"> Middle</font><br>"+"show answer" + "</center></html>";
    private final String buttonGoodText = "<html><center>" + "<font size=\"5\">Good </font><br>"+"next word" + "</center></html>";
    private final String buttonNextText = "<html><center>" + "<font size=\"5\">Next </font>" + "</center></html>";


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
        this.setSize(500,150);
        this.setVisible(true);
    }


    //TODO: replace with spaced repetition logic
    private void repeatLogic(){
        this.cards = controller.getDictionaryCards(dictionaryName);
        showCardQuestion(cards.get(0));
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
        JButton button1 = new JButton(buttonBadText);
        JButton button2 = new JButton(buttonMiddleText);
        JButton button3 = new JButton(buttonGoodText);
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
        JButton button = new JButton(buttonNextText);
        button.setBackground(Color.cyan);
        button.addActionListener(this);
        controlPanel.add(button);
        controlPanel.setVisible(true);
    }


    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        int learnDelta;
        switch (s) {
            case buttonBadText:
                learnDelta = 1;
                controller.updateCard(dictionaryName,currentCard,learnDelta,true );
                showCardAnswer(currentCard);
                break;
            case buttonMiddleText:
                learnDelta = 2;
                controller.updateCard(dictionaryName,currentCard, learnDelta,true);
                showCardAnswer(currentCard);
                break;
            case buttonGoodText:
                learnDelta = 3;
                controller.updateCard(dictionaryName,currentCard, learnDelta,true);
                repeatLogic();
                break;
            case buttonNextText:
                repeatLogic();
                break;
        }
    }
}
