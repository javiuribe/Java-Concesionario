import org.example.controlador.ControladorCliente;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControladorClienteTest {
    private ControladorCliente controladorCliente;

    @BeforeEach
    void setUp() throws SQLException {
        controladorCliente = new ControladorCliente();
    }

    @Test
    void testRegistrarCliente() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        assertEquals(1, cliente.getId());
        assertEquals(cliente, controladorCliente.buscarCliente(1));
    }

    @Test
    void testRegistrarClienteConCoches() throws SQLException{
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche("1123HAR", "Ford", "Fiesta", new Date(), cliente));
        coches.add(new Coche("999HAR", "Opel", "Corsa", new Date(), cliente));
        cliente.setCoches(coches);
        controladorCliente.guardarClienteConCoches(cliente);
        assertEquals(1, cliente.getId());
        Cliente clienteEncontrado = controladorCliente.buscarCliente(1);
        System.out.println("Cliente encontrado: " + clienteEncontrado.getCoches());
        assertEquals(cliente, clienteEncontrado);
    }

    @Test
    void testBuscarClienteCorrecto() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        Cliente loggedInCliente = controladorCliente.buscarCliente(1);
        assertEquals(cliente, loggedInCliente);
    }

    @Test
    void testBuscarClienteIncorrecto() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controladorCliente.buscarCliente(2);
        });
        assertEquals("No se encontró el cliente con el id:2", exception.getMessage());
    }

    @Test
    void testActualizarCliente() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        Cliente clienteRegistrado = controladorCliente.buscarCliente(cliente.getId());
        String nuevoNombre = "Carlos";
        String nuevoTelefono = "987654321";
        clienteRegistrado.setNombre(nuevoNombre);
        clienteRegistrado.setTelefono(nuevoTelefono);
        controladorCliente.actualizarCliente(clienteRegistrado);
        assertEquals(clienteRegistrado, controladorCliente.buscarCliente(cliente.getId()));
    }


}
