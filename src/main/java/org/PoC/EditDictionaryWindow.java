package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

class EditDictionaryWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private JTextField textFieldNewDictionary;
    private JPanel mainPanel;

    public EditDictionaryWindow(Controller controller,ArrayList<Card> cards){
        this.controller = controller;
        this.setTitle("Dictionary content");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        JButton buttonSave = new JButton("Save");
        JButton buttonCancel = new JButton("Cancel");
        buttonSave.addActionListener(this);
        buttonCancel.addActionListener(this);
        JPanel controlPanel = new JPanel();
        controlPanel.add(buttonCancel);
        controlPanel.add(buttonSave);
        mainPanel.add(controlPanel);
        createDictionaryTable(cards);
        this.setSize(600,500);
        this.add(mainPanel);
        this.setVisible(true);
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
        } //TODO add action for SAVE button
    }

    /**
     * Method to create new window with request of the new dictionary name
     */
    public void createDictionaryTable(ArrayList<Card> cards){
        JPanel tablePanel = new JPanel();
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
        mainPanel.add(tablePanel);
    }
}
