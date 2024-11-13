package org.example.controlador.dao.impl;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.CocheDao;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void saveWithCars(Cliente cliente) throws SQLException {
        String queryCliente = "INSERT INTO cliente (dni, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(queryCliente, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmtCliente.setString(1, cliente.getDni());
            stmtCliente.setString(2, cliente.getNombre());
            stmtCliente.setString(3, cliente.getApellidos());
            stmtCliente.setString(4, cliente.getTelefono());
            stmtCliente.executeUpdate();

            ResultSet rs = stmtCliente.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }
        }
    }

    public Cliente findByDni(String dni) throws SQLException {
        String queryCliente = "SELECT cliente.dni, cliente.nombre, cliente.apellidos, cliente.telefono," +
                "coche.matricula, coche.marca, coche.modelo, coche.fecha " +
                "FROM cliente " +
                "LEFT JOIN coche ON cliente.dni = coche.propietario " +
                "WHERE cliente.dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
            stmtCliente.setString(1, dni);
            try (ResultSet rs = stmtCliente.executeQuery()) {
                Cliente cliente = null;
                List<Coche> coches = new ArrayList<>();
                while (rs.next()) {
                    if (cliente == null) {
                        cliente = new Cliente(
                                rs.getString("dni"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("telefono")
                        );
                    }
                    Coche coche = new Coche(
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getDate("fecha")
                    );
                    coches.add(coche);
                    if (cliente != null) {
                        cliente.setCoches(coches);
                    }
                    return cliente;
                }
                return cliente;
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
    public void delete(Cliente cliente) throws SQLException {
        String query = "DELETE FROM cliente WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cliente.getDni());
            stmt.executeUpdate();
        }
    }
}
