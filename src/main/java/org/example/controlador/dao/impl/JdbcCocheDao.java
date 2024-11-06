package org.example.controlador.dao.impl;

import org.example.controlador.dao.CocheDao;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import static org.example.utils.DatabaseConf.*;

import java.sql.*;

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
            stmt.setString(5, coche.getPropietario().getDni());
            stmt.executeUpdate();
        }
    }

    @Override
    public Coche findByMatricula(String dni) throws SQLException {
        String queryCoche = "SELECT * FROM coche WHERE matricula = ?";
        String queryCliente = "SELECT * FROM cliente WHERE dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmtCoche = conn.prepareStatement(queryCoche);
             PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
            stmtCoche.setString(1, dni);
            try (ResultSet rsCoche = stmtCoche.executeQuery()) {
                if (rsCoche.next()) {
                    Coche coche = new Coche(
                            rsCoche.getString("matricula"),
                            rsCoche.getString("marca"),
                            rsCoche.getString("modelo"),
                            rsCoche.getDate("fecha")
                    );
                    String dniPropietario = rsCoche.getString("propietario");
                    stmtCliente.setString(1, dniPropietario);
                    try (ResultSet rsCliente = stmtCliente.executeQuery()) {
                        if (rsCliente.next()) {
                            Cliente cliente = new Cliente(
                                    rsCliente.getString("dni"),
                                    rsCliente.getString("nombre"),
                                    rsCliente.getString("apellidos"),
                                    rsCliente.getString("telefono")
                            );
                            coche.setPropietario(cliente);
                        }
                    }
                    return coche;
                }
            }
        }
        return null;
    }


    @Override
    public void updateCoche(Coche coche) throws SQLException {

    }

    @Override
    public void deleteCoche(Coche coche) throws SQLException {

    }
}
