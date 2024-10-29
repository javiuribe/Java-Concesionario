package org.example.controlador;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.impl.JdbcClienteDao;
import org.example.modelo.Cliente;

import java.sql.SQLException;

public class ControladorCliente {
    private ClienteDao clienteDao;

    public ControladorCliente() throws SQLException {
        this.clienteDao = new JdbcClienteDao();
    }

    public void registrarCliente(Cliente cliente) {
        try {
            clienteDao.save(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente leerCliente(String dni) throws SQLException {
        return clienteDao.findByDni(dni);
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        clienteDao.update(cliente);
    }

    public void deleteCliente(Cliente cliente) throws SQLException {
        clienteDao.deleteByDni(cliente.getDni());
    }
}
