package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainWindow extends JFrame implements ActionListener {
    private JPanel mainPanel;

    public MainWindow(){
        addWindow();
    }

    public void addWindow(){
        this.setTitle("SpacedDict");
        this.setSize(1000,700);
        this.setLayout(null);

        mainPanel = new JPanel();
        mainPanel.setSize(this.getWidth(),this.getHeight());
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);

        int deltaX = 50;
        addControls("New dictionary",10,10);
        addControls("Edit dictionary",10,10+deltaX);
        addControls("Show all dictionaries",10,10+2*deltaX);
        addControls("Repeat",10,10+3*deltaX);

        this.add(mainPanel);

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
            System.out.println("\n Create panel");
            JLabel l = new JLabel("this is a window");
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.add(l);
            panel.setSize(100,40);

            panel.setBackground(Color.white);
            panel.setLocation(10, 10);
            //panel.setVisible(true);
            this.add(panel);
        }
    }
}
