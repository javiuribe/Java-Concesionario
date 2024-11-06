package org.example.controlador.dao.impl;

import org.example.controlador.dao.ClienteDao;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import java.sql.*;
import static org.example.utils.DatabaseConf.*;

public class JdbcClienteDao implements ClienteDao {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void save(Cliente cliente) throws SQLException {
        String queryCliente = "INSERT INTO cliente (dni, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(queryCliente);
        ) {
            stmtCliente.setString(1, cliente.getDni());
            stmtCliente.setString(2, cliente.getNombre());
            stmtCliente.setString(3, cliente.getApellidos());
            stmtCliente.setString(4, cliente.getTelefono());
            stmtCliente.executeUpdate();
        }
    }

    @Override
    public Cliente findByDni(String dni) throws SQLException {
        String queryCliente = "SELECT * FROM cliente WHERE dni = ?";
        String queryCoche = "SELECT * FROM coche WHERE propietario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(queryCliente);
             PreparedStatement stmtCoche = conn.prepareStatement(queryCoche);
        )
        {
            stmtCliente.setString(1, dni);
            try (ResultSet rs = stmtCliente.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente =  new Cliente(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono")
                    );
                    stmtCoche.setString(1, dni); // Usar el DNI del cliente como parámetro para la consulta de coches
                    try (ResultSet rsCoche = stmtCoche.executeQuery()) {
                        while (rsCoche.next()) {
                            // Crear el objeto Coche y agregarlo a la lista de coches del cliente
                            Coche coche = new Coche(
                                    rsCoche.getString("matricula"),
                                    rsCoche.getString("marca"),
                                    rsCoche.getString("modelo"),
                                    rsCoche.getDate("fecha"),
                                    cliente
                            );
                            cliente.addCoche(coche); // Método que agrega el coche a la lista de coches del cliente
                        }
                    }
                    return cliente;
                }
                return null;
            }
        }
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        String query = "UPDATE cliente SET nombre = ?, apellidos = ?, telefono = ? WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDni());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteByDni(String dni) throws SQLException {
        String query = "DELETE FROM cliente WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dni);
            stmt.executeUpdate();
        }
    }
}
