package org.example;

import org.example.controlador.ControladorCliente;
import org.example.modelo.Cliente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.LogManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ControladorCliente controlador = null;
        try {
            controlador = new ControladorCliente();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       // controlador.registrarCliente(new Cliente("1111A", "Javier", "Uribe", "+3412312312"));
        try {
            Cliente cliente =controlador.leerCliente("1111A");
            System.out.println(cliente);
            controlador.deleteCliente(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}