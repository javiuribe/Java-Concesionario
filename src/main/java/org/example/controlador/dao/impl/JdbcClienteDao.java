package org.example.controlador.dao.impl;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.CocheDao;
import org.example.modelo.Cliente;

import java.sql.*;
import static org.example.utils.DatabaseConf.*;

public class JdbcClienteDao implements ClienteDao {
    private CocheDao cocheDao;

    public JdbcClienteDao() throws SQLException {
        this.cocheDao = new JdbcCocheDao();
    }

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

    public Cliente findByDni(String dni) throws SQLException {
        String queryCliente = "SELECT * FROM cliente WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
            stmtCliente.setString(1, dni);
            try (ResultSet rs = stmtCliente.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono")
                    );
                    cliente.setCoches(cocheDao.findByPropietario(dni));
                    return cliente;
                }
            }
        }
        return null;
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
    public void delete(Cliente cliente) throws SQLException {
        String query = "DELETE FROM cliente WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getDni());
            stmt.executeUpdate();
        }
    }
}
