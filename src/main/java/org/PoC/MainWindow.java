package org.PoC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class MainWindow extends JFrame implements ActionListener {
    private final Controller controller;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel leftPanel1;
    private JPanel leftPanel2;
    private JPanel dictionaryPanel;
    private ArrayList<String> currentDictionariesList;

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

        leftPanel = new JPanel();
        leftPanel.setLayout(new CardLayout());
        showMainControlPanel();
        showDictionaries(dictionaries);
        this.add(leftPanel);
        this.setVisible(true);
    }

    /**
     * Add control buttons to the left
     */
    private void addButton(String buttonText,JPanel panel){
        JButton buttonNew=new JButton(buttonText);
        panel.add(buttonNew);
        buttonNew.addActionListener(this);
    }
    /**
     * Add control buttons to the left panel 1
     */
    public void showMainControlPanel(){
        leftPanel.setVisible(false);
        leftPanel.removeAll();
        leftPanel.setBounds(new Rectangle(15,10,190,200));
        leftPanel1 = new JPanel();
        leftPanel1.setLayout(new GridLayout(0,1,10,10));
        addButton("New dictionary",leftPanel1);
        addButton("Edit list",leftPanel1);
        addButton("Settings",leftPanel1);
        addButton("Exit",leftPanel1);
        leftPanel.add(leftPanel1);
        leftPanel.setVisible(true);
    }

    /**
     * Add control buttons to the left panel 2
     */
    public void showEditControlPanel(){
        leftPanel.setVisible(false);
        leftPanel.removeAll();
        leftPanel.setBounds(new Rectangle(15,10,190,250));
        leftPanel2 = new JPanel();
        leftPanel2.setLayout(new GridLayout(0,1,10,10));
        addButton("Add card",leftPanel2);
        addButton("Edit card",leftPanel2);
        addButton("Rename dictionary",leftPanel2);
        addButton("Remove dictionary",leftPanel2);
        addButton("Back",leftPanel2);
        leftPanel.add(leftPanel2);
        leftPanel.setVisible(true);
    }

    /**
     * Add buttons with Dictionaries to the right
     */
    private void showDictionaries(ArrayList<String> dictionaries){
        currentDictionariesList = dictionaries;
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
        String string = actionEvent.getActionCommand();
        if (string.equals("New dictionary")) {
            controller.newDictionaryWindow();
        } else if (string.equals("Edit list")) {
            controller.enterEditMode();
            System.out.println("Edit mode!");
        } else if (string.equals("Back")) {
            controller.exitEditMode();
            System.out.println("Exit edit mode!");
        }else if (currentDictionariesList.contains(string) && !controller.isEditMode()){
            System.out.println("Repeat dictionary: " + string);
        }else if (currentDictionariesList.contains(string) && controller.isEditMode()){
            System.out.println("Remove dictionary: " + string);
            controller.removeDictionary(string);
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
