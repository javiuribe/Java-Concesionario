package org.example.controlador.dao;

import org.example.modelo.Coche;

import java.sql.SQLException;
import java.util.List;

public interface CocheDao {

    void save(Coche coche) throws SQLException;
    Coche findByMatricula(String matricula) throws SQLException;
    void update(Coche coche) throws SQLException;
    void delete(Coche coche) throws SQLException;

    List<Coche> findByPropietario(String dniPropietario) throws SQLException;
}
