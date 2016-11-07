package ru.test.services;

import java.sql.*;

/**
 * Автор: Туров Данил
 * Скрывает реализацию доступа к БД
 * 02.11.2016.
 */
public class DBService {
    // JDBC драйвер
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    //БД URL
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    //логин/пароль для доступа к БД
    private static final String USER = "postgres";
    private static final String PASS = "599713";
    //Коннекшен к БД
    private static Connection connection;

    /**
     * Получаем подключение к БД
     * @return connection к БД
     */
    private static Connection getConnection() {
        if (connection == null) {
            try {
                //Регистрация JDBC драйвера
                Class.forName(JDBC_DRIVER);
                System.out.println("Подключение к БД...");

                //Открываем коннекшен
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                System.out.println("Не могу найти драйвер.");
            } catch (SQLException e) {
                System.out.println("Не могу подключиться в БД.");
            }

        }
        return connection;
    }

    /**
     * Получение стейтмента
     * @return statement
     */
    public static Statement getStatement() {
        System.out.println("Создание стейтмента...");
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("Не могу создать стейтмент.");
        }
        return statement;
    }

    /**
     * Закрывает подключение к БД
     * @return boolean, закрыто или нет подключение.
     */
    public boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


}
