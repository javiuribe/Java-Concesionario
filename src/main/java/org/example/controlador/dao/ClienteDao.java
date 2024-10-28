package org.example.controlador.dao;

import org.example.modelo.Cliente;

public interface ClienteDao {

    void createCliente(Cliente cliente);
    Cliente readClienteById(int id);
    Cliente readClienteByEmail(String email);
    void updateCliente(Cliente cliente);
    void removeCliente(Cliente cliente);
}
