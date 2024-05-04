package org.PoC;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Library {

    private Controller controller;
    String dbPath = "src/main/resources/SQLite/dictionaries.db";

    public Library( ){
    }


    /**
     * returns words from specific dictionary
     * @param dictionaryName name of the dictionary
     */
    public ArrayList<Card> getDictionaryCards(String dictionaryName) {
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
                cards.add(new Card(word1,word2));
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
                    + "word2 TEXT NOT NULL,"
                    +"lastRepeat INT NOT NULL,"
                    +"learnIndex INT NOT NULL"
                    +");";
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
    public ArrayList<String> getDictionaries(){
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
            dictionaries.sort(new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    return s.compareTo(t1);
                }
            });
            return dictionaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Returns list of all dictionaries names
     */
    public void removeDictionary(String tableName){
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
            // lastRepeat: number of minutes from minimum time to current time
            long lastRepeat = Duration.between(LocalDateTime.MIN,LocalDateTime.now()).toMinutes();
            int learnIndex = 0;
            String createTableSQL = "insert into "
                    + dictionaryName + " values('"
                    + word1 + "','"
                    + word2 + "','"
                    + lastRepeat + "',' "
                    + learnIndex +"');";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            statement.executeUpdate();
            System.out.println("Card added to the "+dictionaryName);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void updateCardLearnIndex(String dictionaryName,String word1, int newLearnIndex ) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            String createTableSQL = "UPDATE "
                    + dictionaryName + " SET learnIndex = "
                    + newLearnIndex + " WHERE word1='"
                    + word1 + "';";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            statement.executeUpdate();
            System.out.println("Card added to the "+dictionaryName);
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
}
