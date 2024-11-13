package org.example.controlador.dao.impl;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.CocheDao;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import static org.example.utils.DatabaseConf.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCocheDao implements CocheDao {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void save(Coche coche) throws SQLException {
        String queryCoche = "INSERT INTO coche (matricula, marca, modelo, fecha, propietario) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(queryCoche)) {
            stmt.setString(1, coche.getMatricula());
            stmt.setString(2, coche.getMarca());
            stmt.setString(3, coche.getModelo());
            stmt.setDate(4, new java.sql.Date(coche.getFecha().getTime()));
            stmt.setInt(5, coche.getPropietario().getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Coche findByMatricula(String matricula) throws SQLException {
        String queryCoche = "SELECT cliente.dni, cliente.nombre, cliente.apellidos, cliente.telefono," +
                "coche.matricula, coche.marca, coche.modelo, coche.fecha " +
                "FROM coche " +
                "LEFT JOIN cliente ON cliente.dni = coche.propietario " +
                "WHERE cliente.dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmtCoche = conn.prepareStatement(queryCoche)){
            stmtCoche.setString(1, matricula);
            try (ResultSet rs = stmtCoche.executeQuery()) {
                if (rs.next()) {
                    Coche coche = new Coche(
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getDate("fecha")
                    );
                    Cliente cliente = new Cliente(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono")
                    );
                    coche.setPropietario(cliente);
                    return coche;
                }
            }
        }
        return null;
    }


    @Override
    public void update(Coche coche) throws SQLException {
        String query = "UPDATE coche SET marca = ?, modelo = ?, fecha = ?, propietario = ? WHERE matricula = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, coche.getMarca());
            stmt.setString(2, coche.getModelo());
            stmt.setDate(3, new java.sql.Date(coche.getFecha().getTime()));
            stmt.setString(4, coche.getPropietario().getDni());
            stmt.setString(5, coche.getMatricula());
            stmt.executeUpdate();
        }
    }


    @Override
    public void delete(Coche coche) throws SQLException {
        String query = "DELETE FROM coche WHERE matricula = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, coche.getMatricula());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Coche> findByPropietario(String dniPropietario) throws SQLException {
        List<Coche> coches = new ArrayList<>();
        String query = "SELECT * FROM coche WHERE propietario = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dniPropietario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Coche coche = new Coche(
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getDate("fecha")
                    );
                    coches.add(coche);
                }
            }
        }
        return coches;
    }

}
