package org.example.controlador.dao;

import org.example.modelo.Coche;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CocheDao extends Dao<Coche> {

    Optional<Coche> findByMatricula(String matricula) throws SQLException;

    List<Coche> findByPropietario(String dniPropietario) throws SQLException;
}