package ru.test.services;

import java.sql.*;

/**
 * Created by turov on 02.11.2016.
 */
public class DBService {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    //  Database credentials
    private static final String USER = "postgres";
    private static final String PASS = "599713";

    private static Connection connection;

    private static Connection getConnection() {
        if (connection == null) {
            try {
                //STEP 2: Register JDBC driver
                Class.forName(JDBC_DRIVER);
                System.out.println("Connecting to database...");

                //STEP 3: Open a connection
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                System.out.println("Can't find the driver.");
            } catch (SQLException e) {
                System.out.println("Can't connect to the database.");
            }

        }
        return connection;
    }

    public static Statement getStatement() {
        System.out.println("Creating statement...");
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("Can't create statement.");
        }
        return statement;
    }

    public boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


}
