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
    private ArrayList<String> editDictionariesList;

    private final String iconPathDictionary = "src/main/resources/img/book_icon.jpeg";

    public MainWindow(Controller controller){
        this.controller = controller;
    }

    /**
     *  Method to create a new window with control panel and showing existing dictionaries
     * @param dictionaries - ArrayList<String> with dictionary names
     */
    public void createMainWindow(ArrayList<String> dictionaries, int width, int height){
        this.setTitle("SpacedDictionary");
        this.setSize(width,height);
        editDictionariesList = new ArrayList<>();
        this.setLayout(null);
        this.addWindowListener(new WindowAdapter()
        {public void windowClosing(WindowEvent e)
        {
            dispose();
            controller.closeDB();
            System.exit(0);
        }
        });

        leftPanel = new JPanel();
        leftPanel.setLayout(new CardLayout());
        showMainControlPanel();
        if (dictionaries!=null) {
            showDictionaries(dictionaries);
        }
        this.add(leftPanel);
        this.setVisible(true);
    }

    /**
     * Add control buttons to the left
     */
    private JButton addButton(String buttonText,JPanel panel){
        JButton buttonNew=new JButton(buttonText);
        panel.add(buttonNew);
        buttonNew.addActionListener(this);
        return buttonNew;
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
        addButton("Edit dictionary",leftPanel1);
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
        leftPanel.setBounds(new Rectangle(15,10,190,200));
        leftPanel2 = new JPanel();
        leftPanel2.setLayout(new GridLayout(0,1,10,10));
        addButton("Edit cards",leftPanel2);
        addButton("Rename dictionary",leftPanel2);
        addButton("Remove dictionary",leftPanel2);
        addButton("Back",leftPanel2);
        leftPanel.add(leftPanel2);
        leftPanel.setVisible(true);
    }

    /**
     * Add buttons with Dictionaries to the right
     */
    public void showDictionaries(ArrayList<String> dictionaries){
        currentDictionariesList = dictionaries;
        if (rightPanel==null) {
            rightPanel = new JPanel();
        }else{
            rightPanel.setVisible(false);
            rightPanel.removeAll();
        }
        rightPanel.setBackground(Color.gray);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBounds(new Rectangle(240,10,550,500));
        dictionaryPanel = new JPanel();
        dictionaryPanel.setBackground(Color.WHITE);
        dictionaryPanel.setLayout(new GridLayout(0,2,10,10));

        for (String dictionary : dictionaries){
            JButton buttonNew=new JButton(dictionary);
            buttonNew.setPreferredSize(new Dimension(100, 50));
            buttonNew.setMaximumSize(new Dimension(100, 50));
            buttonNew.setMinimumSize(new Dimension(100, 50));
            buttonNew.setHorizontalAlignment(SwingConstants.LEFT);
            buttonNew.setIcon(iconManager(iconPathDictionary));
            buttonNew.setIconTextGap(50);
            dictionaryPanel.add(buttonNew);
            buttonNew.addActionListener(this);
        }

        JScrollPane scrollPane = new JScrollPane(dictionaryPanel);
        rightPanel.add(scrollPane);
        this.add(rightPanel);
        rightPanel.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String string = actionEvent.getActionCommand();
        if (controller.isEditMode()){                   // EDIT CONTROL PANEL
            if (string.equals("Back")) {
                editDictionariesList.clear();
                controller.exitEditMode();
                controller.refreshMainWindow();
                System.out.println("Exit edit mode!");
            }else if (currentDictionariesList.contains(string)) {
                chooseButton(actionEvent);
            }else if (string.equals("Edit cards") && editDictionariesList.size()==1) {
                controller.createEditDictionaryWindow(editDictionariesList.get(0));
            }else if (string.equals("Remove dictionary")) {
                for (String dictionary : editDictionariesList) {
                    controller.removeDictionary(dictionary);
                }
                editDictionariesList.clear();
                showEditControlPanel();
            }
        }else {                                         // MAIN CONTROL PANEL
            if (string.equals("New dictionary")) {
                controller.newDictionaryWindow();
            } else if (string.equals("Edit dictionary")) {
                controller.enterEditMode();
                System.out.println("Edit mode!");
            } else if (currentDictionariesList.contains(string)) {
                controller.repeatDictionary(string);
                System.out.println("\nRepeat dictionary: " + string);
            }else if (string.equals("Exit")) {
                dispose();
                controller.closeDB();
                System.exit(0);
            }
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

    private void setInactiveButtons(){
        leftPanel.setVisible(false);
        leftPanel.removeAll();
        leftPanel.setBounds(new Rectangle(15,10,190,250));
        leftPanel2 = new JPanel();
        leftPanel2.setLayout(new GridLayout(0,1,10,10));
        JButton button2 = addButton("Edit cards",leftPanel2);
        button2.setBackground(Color.WHITE);
        button2.setForeground(Color.lightGray);
        JButton button3 = addButton("Rename dictionary",leftPanel2);
        button3.setBackground(Color.WHITE);
        button3.setForeground(Color.lightGray);
        addButton("Remove dictionary",leftPanel2);
        addButton("Back",leftPanel2);
        leftPanel.add(leftPanel2);
        leftPanel.setVisible(true);
    }

    private void chooseButton(ActionEvent actionEvent){
        String string = actionEvent.getActionCommand();
        JButton buttonSource = (JButton) actionEvent.getSource();

        if (editDictionariesList.contains(string)) {
            buttonSource.setBackground(new JButton().getBackground());
            editDictionariesList.remove(string);
        }else{
            buttonSource.setBackground(Color.CYAN);
            editDictionariesList.add(string);
        }
        if (editDictionariesList.size() > 1) {
            setInactiveButtons();
        } else {
            showEditControlPanel();
        }
    }
}
