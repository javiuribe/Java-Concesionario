package org.example.controlador.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    void save(T entity) throws SQLException;

    Optional<T> findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    void update(T entity) throws SQLException;

    void delete(T entity) throws SQLException;
}
