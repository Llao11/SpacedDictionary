package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private Controller controller;
    String dbPath = "src/main/resources/SQLite/dictionaries.db";

    public Library(Controller controller){
        this.controller=controller;
    }


    /**
     * Create databese
     */
    public void createDB() {


        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath+"dictionaries.db");

            // Create a table (example: employees)
            Statement stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
                    + "id INTEGER PRIMARY KEY,"
                    + "first_name TEXT NOT NULL,"
                    + "last_name TEXT NOT NULL,"
                    + "job_title TEXT)";
            stmt.execute(createTableSQL);

            System.out.println("Database created successfully!");

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
    }



    /**
     *
     * Add new dictionary
     * @param dictionaryName - name of a new dictionary
     */
    public void newDict(String dictionaryName) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            // Create a table (example: employees)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + dictionaryName
                    + "(id INTEGER PRIMARY KEY,"
                    + "world1 TEXT NOT NULL,"
                    + "world2 TEXT NOT NULL);";
            PreparedStatement statement = conn.prepareStatement(createTableSQL);
            //statement.setString(1, dictionaryName); // Bind the value to the parameter
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
