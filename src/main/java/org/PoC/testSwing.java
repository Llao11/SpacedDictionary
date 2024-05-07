package org.PoC;


// Java Program to demonstrate
// Simple JscrollPane
import javax.swing.*;
        import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Driver Class
public class testSwing {
    // main function
    public static void main(String[] args) {
        // Create a frame
        JFrame frame = new JFrame("File Chooser Example");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a button that will open the file chooser
        JButton openButton = new JButton("Open File Chooser");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser
                JFileChooser fileChooser = new JFileChooser();
                // Set the selection mode to files only
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                // Show the file chooser dialog
                int returnValue = fileChooser.showOpenDialog(null);

                // Check if a file was selected
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
            }
        });

        // Add the button to the frame
        frame.getContentPane().add(openButton);
        // Display the frame
        frame.setVisible(true);
    }
}



