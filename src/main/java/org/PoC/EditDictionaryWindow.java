package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class EditDictionaryWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private JPanel mainPanel;
    private ArrayList<Card> cards;
    private JPanel tablePanel;
    private String dictionaryName;

    public EditDictionaryWindow(Controller controller,ArrayList<Card> cards,String dictionary){
        this.cards=cards;
        this.dictionaryName = dictionary;
        this.controller = controller;
        this.setTitle("Dictionary content");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        JButton buttonCancel = new JButton("Cancel");
        JButton buttonAddCard = new JButton("Add card");
        buttonCancel.addActionListener(this);
        buttonAddCard.addActionListener(this);
        JPanel controlPanel = new JPanel();
        controlPanel.add(buttonCancel);
        controlPanel.add(buttonAddCard);
        mainPanel.add(controlPanel);
        tablePanel = new JPanel();
        createDictionaryTable();
        mainPanel.add(tablePanel);
        this.setSize(600,500);
        this.add(mainPanel);
        this.setVisible(true);
    }



    /**
     * Method to create new window with request of the new dictionary name
     */
    public void createDictionaryTable(){
        String[] columnNames = {"Number",
                "Word1",
                "Word2",
                "Last repeat",
                "index"};
        String [][] data = new String[cards.size()][columnNames.length];
        for (int i=0 ; i<cards.size() ; i++){
            data[i][0] = Integer.toString(i);
            data[i][1] = cards.get(i).getWord1();
            data[i][2] = cards.get(i).getWord2();
            data[i][3] = Long.toString(cards.get(i).getLastRepeat());
            data[i][4] = Integer.toString(cards.get(i).getLearnIndex());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollPane);
    }

    public void  refreshTable(){
        tablePanel.setVisible(false);
        this.setVisible(false);
        this.dispose();
        controller.createEditDictionaryWindow(dictionaryName);
    }


    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Cancel")) {
            this.setVisible(false);
            this.dispose();
        }else if (s.equals("Add card")) {
            controller.addCardWindow(dictionaryName);
        }
    }
}
