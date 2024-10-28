package org.example.controlador.dao.impl;

import org.example.controlador.dao.ClienteDao;
import org.example.modelo.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcClienteDao implements ClienteDao {
    private static final String URL = "jdbc:mysql://localhost:3306/concesionario";
    public static final String USER = "root";
    public static final String PASS = "admin";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void createCliente(Cliente cliente) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS cliente (" +
                "dni VARCHAR(255) PRIMARY KEY," +
                "nombre VARCHAR(255) NOT NULL," +
                "admin BOOL DEFAULT FALSE," +
                "email VARCHAR(255) NOT NULL," +
                "telefono VARCHAR(255) NOT NULL," +
                "direccion VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL" +
                ");";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    @Override
    public Cliente readClienteById(int id) {
        return null;
    }

    @Override
    public Cliente readClienteByEmail(String email) {
        return null;
    }

    @Override
    public void updateCliente(Cliente cliente) {

    }

    @Override
    public void removeCliente(Cliente cliente) {

    }
}
