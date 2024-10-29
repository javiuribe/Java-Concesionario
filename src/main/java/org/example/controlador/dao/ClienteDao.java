package org.example.controlador.dao;

import org.example.modelo.Cliente;

import java.sql.SQLException;

public interface ClienteDao {

    void save(Cliente cliente) throws SQLException;
    Cliente findByDni(String dni) throws SQLException;
    void update(Cliente cliente) throws SQLException;
    void deleteByDni(String dni) throws SQLException;
}
