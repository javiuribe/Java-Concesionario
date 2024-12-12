package org.example.controlador;

import org.example.controlador.dao.ClienteDao;
import org.example.controlador.dao.impl.JpaClienteDao;
import org.example.modelo.Cliente;

import java.sql.SQLException;
import java.util.List;

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

    public Cliente buscarPorId(int id) throws SQLException {
        Cliente cliente =  clienteDao.findById(id);
        if (cliente == null){
            throw new IllegalArgumentException("No se encontró el cliente con el id:" + id);
        }
        return cliente;
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteDao.findByNombre(nombre);
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        clienteDao.update(cliente);
    }

    public void deleteCliente(Cliente cliente) throws SQLException {
        clienteDao.delete(cliente);
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        clienteDao.update(cliente);
    }
}
