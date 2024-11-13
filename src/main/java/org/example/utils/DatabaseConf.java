package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConf {

    public static final String URL = "jdbc:mysql://localhost:3306/concesionario";
    public static final String USER = "root";
    public static final String PASS = "admin";
    public static final String CREATE_TABLE_CLIENTE ="CREATE TABLE IF NOT EXISTS CLIENTE(" +
            "    id int PRIMARY KEY AUTO_INCREMENT," +
            "    dni VARCHAR(255) UNIQUE NOT NULL," +
            "    nombre VARCHAR(255) not null," +
            "    apellidos VARCHAR(255) not null," +
            "    telefono VARCHAR(255) NULL UNIQUE" +
            ");";
    public static final String CREATE_TABLE_COCHE =  "CREATE TABLE IF NOT EXISTS COCHE (" +
            "    matricula VARCHAR(255) PRIMARY KEY," +
            "    marca VARCHAR(255) NOT NULL," +
            "    modelo VARCHAR(255) NOT NULL," +
            "    fecha DATE NULL," +
            "    propietario VARCHAR(255)," +
            "    FOREIGN KEY(propietario) REFERENCES CLIENTE(dni) ON DELETE NO ACTION ON UPDATE CASCADE" +
            ");";
    public static final String DROP_TABLE_CLIENTE = "DROP TABLE IF EXISTS CLIENTE";
    public static final String DROP_TABLE_COCHE = "DROP TABLE IF EXISTS COCHE";


    public static void createTables() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_CLIENTE);
            statement.executeUpdate(CREATE_TABLE_COCHE);

        }
    }

    public static void dropAndCreateTables() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE_COCHE);
            statement.executeUpdate(DROP_TABLE_CLIENTE);
            createTables();
        }
    }
}
