import org.example.controlador.ControladorCliente;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;
import org.example.utils.DatabaseConf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorClienteTest {
    private ControladorCliente controladorCliente;

    @BeforeEach
    void setUp() throws SQLException {
        DatabaseConf.dropAndCreateTables();
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
        Cliente loggedInCliente = controladorCliente.buscarCliente(1);
        assertEquals(2, loggedInCliente.getCoches().size());
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


}
