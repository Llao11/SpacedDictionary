package org.PoC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class testDB {
        public static void main(String[] args) {
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
    }
