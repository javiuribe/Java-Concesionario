package org.example.controlador.dao;

import org.example.modelo.Cliente;

import java.sql.SQLException;

public interface ClienteDao {

    void save(Cliente cliente) throws SQLException;
    Cliente findById(int id) throws SQLException;
    void update(Cliente cliente) throws SQLException;
    void delete(Cliente cliente) throws SQLException;
    void saveWithCars(Cliente cliente) throws SQLException;
}
