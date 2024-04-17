package org.PoC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InfoWindow extends JFrame implements ActionListener {

    public InfoWindow(String message){
        this.setTitle(message);
        JButton button = new JButton("OK");
        button.addActionListener(this);
        JLabel label = new JLabel(message);
        JPanel p = new JPanel();
        p.add(label);
        p.add(button);
        this.add(p);
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
        if (s.equals("OK")) {
            this.setVisible(false);
            this.dispose();
        }
    }

}
