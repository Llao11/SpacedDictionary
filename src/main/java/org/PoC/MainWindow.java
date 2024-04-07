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
    private JPanel leftPanel;
    private JPanel dictionaryPanel;

    private final String iconPathDictionary = "src/main/resources/img/book_icon.jpeg";

    public MainWindow(Controller controller){
        this.controller = controller;
    }

    /**
     *  Method to create a new window with control panel and showing existing dictionaries
     * @param dictionaries - ArrayList<String> with dictionary names
     */
    public void createMainWindow(ArrayList<String> dictionaries, int width, int height){
        this.setTitle("SpacedDict");
        this.setSize(width,height);

        this.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {public void windowClosing(WindowEvent e)
        {
            dispose();
            System.exit(0);
        }
        });

        showControls();
        showDictionaries(dictionaries);
        this.setVisible(true);
    }

    /**
     * Add control buttons to the left
     */
    private void addButton(String buttonText){
        JButton buttonNew=new JButton(buttonText);
        leftPanel.add(buttonNew);
        buttonNew.addActionListener(this);
    }
    /**
     * Add control buttons to the left
     */
    private void showControls(){
        leftPanel = new JPanel();
        leftPanel.setBounds(new Rectangle(20,10,200,400));
        leftPanel.setLayout(new GridLayout(0,1,10,10));
        addButton("New dictionary");
        addButton("Edit dictionary");
        addButton("Show all dictionaries");
        addButton("Repeat");
        this.add(leftPanel);
    }

    /**
     * Add buttons with Dictionaries to the right
     */
    private void showDictionaries(ArrayList<String> dictionaries){
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.gray);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setBounds(new Rectangle(240,10,550,500));
        dictionaryPanel = new JPanel();
        dictionaryPanel.setBackground(Color.WHITE);
        dictionaryPanel.setLayout(new GridLayout(0,2,10,10));

        for (String dictionary : dictionaries){
            JButton buttonNew=new JButton(dictionary);
            buttonNew.setPreferredSize(new Dimension(100, 50));
            buttonNew.setHorizontalAlignment(SwingConstants.LEFT);
            buttonNew.setIcon(iconManager(iconPathDictionary));
            buttonNew.setIconTextGap(50);
            dictionaryPanel.add(buttonNew);
            buttonNew.addActionListener(this);
        }

        JScrollPane scrollPane = new JScrollPane(dictionaryPanel);
        rightPanel.add(scrollPane);
        this.add(rightPanel);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s.equals("New dictionary")) {
            controller.newDictionaryWindow();
        } else if (s.equals("assss")) {
        }
    }

    /**
     * // TODO change the icon size in image file and remove this part
     *   Set icon to the button and rescale it
     * @return
     */
    private ImageIcon iconManager(String pathToImage){
        ImageIcon originalIcon = new ImageIcon(pathToImage);
        Image originalImage = originalIcon.getImage();
        int newWidth = 40;
        int newHeight = 40;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

}
