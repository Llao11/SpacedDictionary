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
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(label1)
                        .addComponent(textFieldWord1)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label2)
                                .addComponent(textFieldWord2))
                        .addComponent(button)
        );
//        layout.setVerticalGroup(
//                layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(c1)
//                                .addComponent(c2)
//                                .addComponent(c3))
//                        .addComponent(c4)
//        );
        pack();
//        panel.add(label1);
//        panel.add(label2);
//        panel.add(textFieldWord1);
//        panel.add(textFieldWord2);
//        panel.add(button);
        this.add(panel);
        this.setSize(300,100);
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
