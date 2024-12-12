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
        assertEquals(cliente, controladorCliente.buscarPorId(1));
    }

    @Test
    void testRegistrarClienteConCoches() throws SQLException{
        String dni = "12345678A";
        String nombre = "Ana";
        String apellido = "Rodríguez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        cliente.addCoche(new Coche("1123HAR", "Ferrari", "Focus", new Date(), cliente));
        cliente.addCoche(new Coche("999HAR", "Opel", "Astra", new Date(), cliente));
        controladorCliente.guardarClienteConCoches(cliente);
        assertEquals(1, cliente.getId());
        Cliente clienteEncontrado = controladorCliente.buscarPorId(1);
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
        Cliente loggedInCliente = controladorCliente.buscarPorId(1);
        assertEquals(cliente, loggedInCliente);
    }

    @Test
    void buscarClientePorNombre() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        assertEquals(cliente, controladorCliente.buscarPorNombre(nombre).getFirst());
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
            controladorCliente.buscarPorId(2);
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
        Cliente clienteRegistrado = controladorCliente.buscarPorId(cliente.getId());
        String nuevoNombre = "Carlos";
        String nuevoTelefono = "987654321";
        clienteRegistrado.setNombre(nuevoNombre);
        clienteRegistrado.setTelefono(nuevoTelefono);
        controladorCliente.actualizarCliente(clienteRegistrado);
        assertEquals(clienteRegistrado, controladorCliente.buscarPorId(cliente.getId()));
    }


}
