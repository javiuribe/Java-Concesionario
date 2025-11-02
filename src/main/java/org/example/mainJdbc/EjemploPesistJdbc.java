package org.example.mainJdbc;

import org.example.utils.DatabaseConf;

import java.sql.*;

import static java.sql.DriverManager.getConnection;
import static org.example.utils.DatabaseConf.*;

public class EjemploPesistJdbc {
    public static void main(String[] args) throws SQLException {
        DatabaseConf.dropAndCreateTables();
        String insertCliente = """
                    INSERT INTO cliente (dni, nombre, apellidos, telefono)
                    VALUES (?, ?, ?, ?)
                """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmtCliente = conn.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {
        }

    }
}

