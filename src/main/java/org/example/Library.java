package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Library {


    /**
     * Create databese
     */
    public void createDB() {
        String dbPath = "src/main/resources/SQLite/database.db"; // Update with the correct path

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

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
     * @param name - name of a new dictionary
     */
    public void newDict(String name) {

    }

    /**
     * Returns list of all dictionaries names
     */
    public List<String> getDicts(){
        return null;
    }

    /**
     * Print all dictionaries
     */
    public void printDicts(){
    }

    /**
     *  Checks if there are dictionaries
     * @return
     */
    public boolean isEmpty(){
        return false;
    }
}
