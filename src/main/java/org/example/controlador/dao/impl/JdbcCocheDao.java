package org.example.controlador.dao.impl;

import org.example.controlador.dao.CocheDao;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import static org.example.utils.DatabaseConf.*;

import java.sql.*;

public class JdbcCocheDao implements CocheDao {


    public JdbcCocheDao() throws SQLException {
        if (CREATE_TABLE) {
            crearTablas();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void crearTablas() throws SQLException {
        if (CREATE_TABLE) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS coche (" +
                    "matricula VARCHAR(255) PRIMARY KEY," +
                    "marca VARCHAR(255) NOT NULL," +
                    "modelo VARCHAR(255) NOT NULL," +
                    "fecha VARCHAR(255) NOT NULL," +
                    "propietario VARCHAR(255) NOT NULL," +
                    "FOREIGN KEY (propietario) REFERENCES cliente(dni)" +
                    ");";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("Tabla 'Coche' creadada Correctamente");
            }
        }
    }

    @Override
    public void createCoche(Coche coche) throws SQLException {
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
    public Coche readCocheByMatricula(String dni) throws SQLException {
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
