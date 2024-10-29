package org.example.controlador.dao;

import org.example.modelo.Cliente;

import java.sql.SQLException;

public interface ClienteDao {

    void createCliente(Cliente cliente) throws SQLException;
    Cliente readClienteByDni(String dni) throws SQLException;
    void updateCliente(Cliente cliente) throws SQLException;
    void deleteCliente(Cliente cliente) throws SQLException;
}
