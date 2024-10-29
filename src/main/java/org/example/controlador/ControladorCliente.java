package org.example.controlador;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.impl.JdbcClienteDao;
import org.example.modelo.Cliente;

import java.sql.SQLException;

import java.util.logging.Logger;

public class ControladorCliente {
    private ClienteDao clienteDao = null;
    private static final Logger logger = Logger.getLogger(ControladorCliente.class.getName());

    public ControladorCliente() throws SQLException {
        this.clienteDao = new JdbcClienteDao();
    }

    public void registrarCliente(Cliente cliente) {
        try {
            clienteDao.createCliente(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente leerCliente(String dni) throws SQLException {
        return clienteDao.readClienteByDni(dni);
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        clienteDao.updateCliente(cliente);
    }

    public void deleteCliente(Cliente cliente) throws SQLException {
        clienteDao.deleteCliente(cliente);
    }
}
