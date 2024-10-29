package org.example;

import org.example.controlador.ControladorCliente;
import org.example.controlador.ControladorCoche;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import java.sql.SQLException;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            ControladorCliente controladorCliente = new ControladorCliente();
            ControladorCoche controladorCoche = new ControladorCoche();
            //controladorCliente.registrarCliente(new Cliente("1111A", "Javier", "Uribe", "+3412312312"));
            Cliente cliente = controladorCliente.leerCliente("1111A");
            controladorCoche.agregarCoche(new Coche("1111R", "Ford", "Fiesta", new Date(), cliente));
            cliente = controladorCliente.leerCliente("1111A");
            System.out.println(cliente);
            cliente.getCoches().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}