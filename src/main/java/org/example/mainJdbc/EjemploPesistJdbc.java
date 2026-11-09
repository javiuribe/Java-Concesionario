package org.example.mainJdbc;

import org.example.utils.DatabaseConf;

import java.sql.*;

import static org.example.utils.DatabaseConf.*;

public class EjemploPesistJdbc {

    public static void main(String[] args) throws SQLException {

        // Crear las tablas desde cero
        DatabaseConf.dropAndCreateTables();

        String insertCliente = """
                INSERT INTO cliente (dni, nombre, apellidos, telefono)
                VALUES (?, ?, ?, ?)
                """;

        String insertCoche = """
                INSERT INTO coche (matricula, marca, modelo, propietario)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // Desactivamos autocommit para controlar la transacción
            conn.setAutoCommit(false);

            try {
                int clienteId;

                // 1. Insertar cliente
                try (PreparedStatement stmtCliente =
                             conn.prepareStatement(insertCliente,
                                     Statement.RETURN_GENERATED_KEYS)) {

                    stmtCliente.setString(1, "12345678A");
                    stmtCliente.setString(2, "Juan");
                    stmtCliente.setString(3, "Pérez García");
                    stmtCliente.setString(4, "600123456");

                    stmtCliente.executeUpdate();

                    // Recuperar ID generado
                    try (ResultSet rs = stmtCliente.getGeneratedKeys()) {
                        if (rs.next()) {
                            clienteId = rs.getInt(1);
                        } else {
                            throw new SQLException(
                                    "No se ha podido recuperar el ID del cliente"
                            );
                        }
                    }
                }

                System.out.println("Cliente insertado con ID: " + clienteId);

                // 2. Insertar coches asociados al cliente
                try (PreparedStatement stmtCoche =
                             conn.prepareStatement(insertCoche)) {

                    stmtCoche.setString(1, "1234ABC");
                    stmtCoche.setString(2, "Seat");
                    stmtCoche.setString(3, "León");
                    stmtCoche.setInt(4, clienteId);
                    stmtCoche.executeUpdate();

                    stmtCoche.setString(1, "5678DEF");
                    stmtCoche.setString(2, "Toyota");
                    stmtCoche.setString(3, "Corolla");
                    stmtCoche.setInt(4, clienteId);
                    stmtCoche.executeUpdate();
                }

                // 3. Confirmar transacción
                conn.commit();

                System.out.println("Coches insertados correctamente.");

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }

        // 4. Consultar los datos insertados
        consultarClientes();
    }

    private static void consultarClientes() throws SQLException {

        String sql = """
                SELECT c.id, c.dni, c.nombre, c.apellidos,
                       co.matricula, co.marca, co.modelo
                FROM cliente c
                LEFT JOIN coche co ON c.id = co.propietario
                """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- CLIENTES Y SUS COCHES ---");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | DNI: " + rs.getString("dni") +
                                " | Nombre: " + rs.getString("nombre") +
                                " | Apellidos: " + rs.getString("apellidos") +
                                " | Matrícula: " + rs.getString("matricula") +
                                " | Marca: " + rs.getString("marca") +
                                " | Modelo: " + rs.getString("modelo")
                );
            }
        }
    }
}

