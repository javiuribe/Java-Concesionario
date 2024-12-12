package org.example.controlador.dao;

import org.example.modelo.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {

    void save(Cliente cliente) throws SQLException;
    Cliente findById(int id) throws SQLException;

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByModelo(String marca, String modelo);

    void update(Cliente cliente) throws SQLException;
    void delete(Cliente cliente) throws SQLException;
}
