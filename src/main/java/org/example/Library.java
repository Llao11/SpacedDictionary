package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private Controller controller;
    String dbPath = "src/main/resources/SQLite/dictionaries.db";

    public Library( ){
    }


    /**
     * returns words from specific dictionary
     * @param dictionaryName
     */
    public ArrayList<Card> getDictionary(String dictionaryName) {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + dictionaryName +';';
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String word1 = resultSet.getString("Word1");
                String word2 = resultSet.getString("Word2");
                System.out.println(word1 + " - " + word2);
                cards.add(new Card());
            }
            return cards;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Add new dictionary
     * @param dictionaryName - name of a new dictionary
     */
    public void newDictionary(String dictionaryName) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + dictionaryName
                    + "(word1 TEXT NOT NULL,"
                    + "word2 TEXT NOT NULL);";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            statement.executeUpdate();
            System.out.println("Dictionary"+dictionaryName+" created successfully!");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     * Returns list of all dictionaries names
     */
    public List<String> getDictionaries(){
        ArrayList<String> dictionaries = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = conn.createStatement();
            String query = "SELECT name FROM sqlite_master WHERE type='table'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");
                dictionaries.add(tableName);
            }
            return dictionaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Returns list of all dictionaries names
     */
    public void removeDictionariy(String tableName){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = conn.createStatement();
            String query = "DROP TABLE " + tableName + ";";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *  Add new card to the dictionary
     * @param word1 - word 1
     * @param word2 - word 2
     * @param dictionaryName - dictionary name
     */
    public void addCard(String word1, String word2, String dictionaryName) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            String createTableSQL = "insert into " + dictionaryName +" values(' " + word1 + "' , ' " + word2 +"');";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            statement.executeUpdate();
            System.out.println("Dictionary"+dictionaryName+" created successfully!");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }


    /**
     * Print all dictionaries
     */
    public void printDictionaries(){
        if (getDictionaries().isEmpty()){
            System.out.println("There is no dictionaries");
        }else {
            getDictionaries().forEach(System.out::println);
        }
    }

    /**
     *  Checks if there are dictionaries
     * @return
     */
    public boolean isEmpty(){
        return false;
    }
}
