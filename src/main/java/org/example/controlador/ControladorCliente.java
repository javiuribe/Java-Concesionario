package org.example.controlador;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.impl.JdbcClienteDao;
import org.example.controlador.dao.impl.JpaClienteDao;
import org.example.modelo.Cliente;

import java.sql.SQLException;

public class ControladorCliente {
    private ClienteDao clienteDao;

    public ControladorCliente() throws SQLException {
        this.clienteDao = new JpaClienteDao();
    }

    public void registrarCliente(Cliente cliente) throws SQLException {
        clienteDao.save(cliente);
    }

    public void guardarClienteConCoches(Cliente cliente) throws SQLException {
        clienteDao.save(cliente);
    }

    public Cliente buscarCliente(int id) throws SQLException {
        return clienteDao.findById(id);
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        clienteDao.update(cliente);
    }

    public void deleteCliente(Cliente cliente) throws SQLException {
        clienteDao.delete(cliente);
    }
}
