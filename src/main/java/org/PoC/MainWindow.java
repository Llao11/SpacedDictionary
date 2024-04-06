package org.PoC;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class MainWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private JPanel rightPanel;
    private JPanel dictionaryPanel;

    private final String iconPathDictionary = "src/main/resources/img/book_icon.jpeg";

    public MainWindow(Controller controller){
        this.controller = controller;
    }
    public void createMainWindow(ArrayList<String> dictionaries){
        this.setTitle("SpacedDict");
        this.setSize(800,600);

        this.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {public void windowClosing(WindowEvent e)
        {
            dispose();
            System.exit(0);
        }
        });

        int deltaX = 50;
        showControls("New dictionary",10,10);
        showControls("Edit dictionary",10,10+deltaX);
        showControls("Show all dictionaries",10,10+2*deltaX);
        showControls("Repeat",10,10+3*deltaX);

        rightPanel = new JPanel();
        showDictionaries(dictionaries);

        this.add(rightPanel);

        this.setVisible(true);
    }

    /**
     * Add control buttons to the left
     */
    private void showControls(String buttonText, int x, int y){
        JButton buttonNew=new JButton(buttonText);
        buttonNew.setBounds(x,y,200, 30);
        this.add(buttonNew);
        buttonNew.addActionListener(this);
    }

    /**
     * Add buttons with Dictionaries to the right
     */
    private void showDictionaries(ArrayList<String> dictionaries){
        rightPanel.setBackground(Color.gray);
        rightPanel.setSize(100,100);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBounds(new Rectangle(250,10,400,300));
        dictionaryPanel = new JPanel();
        dictionaryPanel.setBackground(Color.WHITE);
        dictionaryPanel.setLayout(new BoxLayout(dictionaryPanel, BoxLayout.Y_AXIS));

        int y = 10;
        for (String dictionary : dictionaries){
            dictionaryPanel.add(Box.createVerticalStrut(10));
            JButton buttonNew=new JButton(dictionary);
            buttonNew.setSize(new Dimension(20,200));

            // set icon to the button and rescale it
            ImageIcon originalIcon = new ImageIcon(iconPathDictionary);
            Image originalImage = originalIcon.getImage();
            int newWidth = 30;
            int newHeight = 30;
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            buttonNew.setIcon(scaledIcon);
            buttonNew.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonNew.setPreferredSize(new Dimension(300, 70));
            dictionaryPanel.add(buttonNew);
            buttonNew.addActionListener(this);
        }

        JScrollPane scrollPane = new JScrollPane(dictionaryPanel);
        rightPanel.add(scrollPane);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("New dictionary")) {
            controller.newDictionaryWindow();
        } else if (s.equals("Submit")) {

        }
    }

}
