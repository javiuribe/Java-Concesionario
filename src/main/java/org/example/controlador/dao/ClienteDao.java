package org.example.controlador.dao;

import org.example.modelo.Cliente;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClienteDao extends Dao<Cliente> {

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByModelo(String marca, String modelo);
}