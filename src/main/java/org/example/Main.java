package org.example;

import org.example.controlador.ControladorCliente;
import org.example.controlador.ControladorCoche;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;
import org.example.utils.DatabaseConf;

import java.sql.SQLException;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConf.dropAndCreateTables();
            ControladorCliente controladorCliente = new ControladorCliente();
            ControladorCoche controladorCoche = new ControladorCoche();
            Cliente cliente = new Cliente("11111Z", "Maria", "Gomez", "+34123123123");
            controladorCliente.guardarClienteConCoches(cliente);
            System.out.println(cliente);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}