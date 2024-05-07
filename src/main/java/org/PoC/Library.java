package org.PoC;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Library {

    private Controller controller;
    private Connection conn;
    private String dbPath = "src/main/resources/SQLite";
    private String dbName = "dictionaries.db";

    public Library( ){
        connectToDB();
    }

    public Library(String dbPath){
        this.dbPath = dbPath;
        connectToDB();
    }

    public void connectToDB(){
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath+"/"+dbName);
        } catch (SQLException e) {
            System.out.println(" No connection to DB");
        }
    }

    public void closeDB(){
        try {
        if (conn!=null)  conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * returns words from specific dictionary
     * @param dictionaryName name of the dictionary
     */
    public ArrayList<Card> getDictionaryCards(String dictionaryName) {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM " + dictionaryName +';';
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String word1 = resultSet.getString("Word1");
                String word2 = resultSet.getString("Word2");
                int lastRepeat = resultSet.getInt("lastRepeat");
                int learnIndex = resultSet.getInt("learnIndex");
                System.out.println(word1 + " - " + word2 + " - " + lastRepeat + " - " + learnIndex);
                cards.add(new Card(word1,word2,lastRepeat,learnIndex));
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
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + dictionaryName
                    + "(word1 TEXT NOT NULL,"
                    + "word2 TEXT NOT NULL,"
                    +"lastRepeat INT NOT NULL,"
                    +"learnIndex INT NOT NULL"
                    +");";
            System.out.println(createTableSQL);
            if (conn==null)
                System.out.println("Conn is null");
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            statement.executeUpdate();
            System.out.println("Dictionary"+dictionaryName+" created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     * Returns list of all dictionaries names
     */
    public ArrayList<String> getDictionaries(){
        ArrayList<String> dictionaries = new ArrayList<>();
        try {
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
    public void addNewCard(String word1, String word2, String dictionaryName) {
        try {
            // lastRepeat: number of minutes from minimum time to current time

            // TODO: Correct the last repeat variable to calculate and update it
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
        } catch ( SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     *  Update last repeat for Card in Dictionary name
     * @param dictionaryName - dictionary name
     * @param word1 - first word
     * @param newLearnIndex - new learn index
     */
    public void updateCardLearnIndex(String dictionaryName,String word1, int newLearnIndex ) {
        try {
            String createTableSQL = "UPDATE "
                    + dictionaryName + " SET learnIndex = "
                    + newLearnIndex + " WHERE word1='"
                    + word1 + "';";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            statement.executeUpdate();
            System.out.println("Card added to the "+dictionaryName);
        } catch ( SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     *  Update last repeat for Card in Dictionary name
     * @param dictionaryName - dictionary name
     * @param word1 - first word
     * @param newLearnIndex - new learn index
     */
    public void updateCardAttributes(String dictionaryName,String word1, String newWord1,String newWord2,long newLastRepeat ,int newLearnIndex ) {
        try {
            String createTableSQL = "UPDATE " + dictionaryName
                    + " SET word1 = '" + newWord1 +"',"
                    + " word2 = '" + newWord2 +"',"
                    + " lastRepeat = " + newLastRepeat +","
                    + " learnIndex = " + newLearnIndex
                    + " WHERE word1='" + word1 + "';";
            System.out.println(createTableSQL);
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            statement.executeUpdate();
            System.out.println("Card added to the "+dictionaryName);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }


    /**
     *  Update learn index for Card in Dictionary name
     * @param dictionaryName - dictionary name
     * @param word1 - first word
     * @param newLastRepeat - new last repeat
     */
    public void updateCardLastRepeat(String dictionaryName,String word1, int newLastRepeat ) {
        try {
            String createTableSQL = "UPDATE "
                    + dictionaryName + " SET lastRepeat = "
                    + newLastRepeat + " WHERE word1='"
                    + word1 + "';";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            System.out.println(createTableSQL);
            statement.executeUpdate();
            System.out.println("Card added to the "+dictionaryName);
        } catch ( SQLException e) {
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



    public String getDbPath(){
        return dbPath;
    }

    public void setDbPath(String path){
        this.dbPath = path;
    }
}
