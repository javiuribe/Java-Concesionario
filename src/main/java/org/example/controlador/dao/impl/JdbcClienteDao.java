package org.example.controlador.dao.impl;

import org.example.controlador.dao.ClienteDao;
import org.example.modelo.Cliente;

import java.sql.*;

public class JdbcClienteDao implements ClienteDao {
    private static final String URL = "jdbc:mysql://localhost:3306/concesionario";
    public static final String USER = "root";
    public static final String PASS = "admin";
    public static final boolean CREATE_TABLE = false;

    public JdbcClienteDao() throws SQLException {
        if (CREATE_TABLE) {
            crearTablas();
        }
    }


    public static void crearTablas() throws SQLException {
        if (CREATE_TABLE) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS cliente (" +
                    "dni VARCHAR(255) PRIMARY KEY," +
                    "nombre VARCHAR(255) NOT NULL," +
                    "apellidos VARCHAR(255) NOT NULL," +
                    "telefono VARCHAR(255) NOT NULL" +
                    ");";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
            }
        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void createCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (dni, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellidos());
            stmt.setString(4, cliente.getTelefono());
            stmt.executeUpdate();
        }
    }

    @Override
    public Cliente readClienteByDni(String dni) throws SQLException {
        String query = "SELECT * FROM cliente WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {

    }

    @Override
    public void deleteCliente(Cliente cliente) {

    }
}
