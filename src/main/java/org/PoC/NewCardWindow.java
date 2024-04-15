package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewCardWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private final String dictionaryName;
    private JTextField textFieldWord1;
    private JTextField textFieldWord2;


    /**
     * Method to create new window with request of the new dictionary name
     */
    public NewCardWindow(Controller controller, String dictionaryName){
        this.controller = controller;
        this.dictionaryName=dictionaryName;
        this.setTitle("New Card for the dictionary: "+dictionaryName);

        JButton button = new JButton("Submit");
        button.addActionListener(this);
        JLabel label1 = new JLabel("Enter word 1:");
        textFieldWord1 = new JTextField(16);
        JLabel label2 = new JLabel("Enter word 2:");
        textFieldWord2 = new JTextField(16);
        JPanel panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Create the horizontal group
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(textFieldWord1));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(textFieldWord2));
        hGroup.addGroup(layout.createParallelGroup()
                        .addComponent(button));
        layout.setHorizontalGroup(hGroup);

        // Create the vertical group
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(label2));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(textFieldWord1)
                .addComponent(textFieldWord2)
                .addComponent(button));
        layout.setVerticalGroup(vGroup);

        this.pack();
        this.add(panel);
        this.setSize(400,100);
        this.setVisible(true);
    }



    /**
     * Listner method that sends entered name to the controller
     * @param actionEvent - event variable
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("Submit")) {
            String newWord1 = textFieldWord1.getText();
            String newWord2 = textFieldWord2.getText();
            controller.addCard(dictionaryName,newWord1,newWord2);
            this.setVisible(false);
            this.dispose();
        }
    }


}
