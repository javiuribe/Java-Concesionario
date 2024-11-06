package org.example.controlador.dao;

import org.example.modelo.Coche;

import java.sql.SQLException;

public interface CocheDao {

    void save(Coche coche) throws SQLException;
    Coche findByMatricula(String dni) throws SQLException;
    void updateCoche(Coche coche) throws SQLException;
    void deleteCoche(Coche coche) throws SQLException;
}
