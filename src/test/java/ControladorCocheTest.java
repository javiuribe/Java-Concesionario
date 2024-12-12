import org.example.controlador.ControladorCliente;
import org.example.controlador.ControladorCoche;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ControladorCocheTest {
    private ControladorCoche controladorCoche;
    private ControladorCliente controladorCliente;

    @BeforeEach
    void setUp() throws SQLException {
        controladorCoche = new ControladorCoche();
        controladorCliente = new ControladorCliente();
    }

    @Test
    void guardarCoche() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        Coche coche1 = new Coche("1123HAR", "Ford", "Fiesta", new Date(), cliente);
        controladorCoche.guardarCoche(coche1);
        //El propietario hay que sacarlo de coche1 para que esté persistido y tenga id
        Coche coche2 = new Coche("999HAR", "Opel", "Corsa", new Date(), coche1.getPropietario());
        controladorCoche.guardarCoche(coche2);
    }

    @Test
    void leerCoche() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        String matricula = "1123HAR";
        String marca = "Toyota";
        String modelo = "Corolla";
        Coche coche = new Coche(matricula, marca, modelo, new Date());
        coche.setPropietario(cliente);
        controladorCoche.guardarCoche(coche);
        coche = controladorCoche.buscarPorMatricula(matricula);
        System.out.println(coche);
        System.out.println(coche.getPropietario());
    }

    @Test
    void updateCoche() throws SQLException {
        // Crear cliente
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);

        // Registrar cliente (simulando que ya está guardado en la base de datos)
        controladorCliente.registrarCliente(cliente);

        // Crear coche asociado al cliente
        Coche coche = new Coche("1123HAR", "Ford", "Fiesta", new Date(), cliente);

        // Registrar el coche en la base de datos
        controladorCoche.guardarCoche(coche);

        // Verificar que el coche se guardó correctamente
        Coche cocheGuardado = controladorCoche.buscarPorMatricula("1123HAR");
        assertNotNull(cocheGuardado, "El coche debería estar guardado en la base de datos.");

        // Actualizar coche
        cocheGuardado.setMatricula("2222XYZ");
        cocheGuardado.setMarca("Toyota");
        cocheGuardado.setModelo("Corolla");

        // Llamar al método update para guardar los cambios
        controladorCoche.actualizarCoche(cocheGuardado);

        // Verificar que los cambios se han guardado correctamente
        Coche cocheActualizado = controladorCoche.buscarPorMatricula("2222XYZ");
        assertNotNull(cocheActualizado, "El coche debería estar actualizado en la base de datos.");

        // Verificar que los nuevos detalles se han persistido
        assertEquals("Toyota", cocheActualizado.getMarca(), "La marca debería ser 'Toyota'.");
        assertEquals("Corolla", cocheActualizado.getModelo(), "El modelo debería ser 'Corolla'.");
        assertEquals("2222XYZ", cocheActualizado.getMatricula(), "La matrícula debería ser '2222XYZ'.");

        // Verificar que el coche original con la matrícula anterior ya no existe
        Coche cocheOriginal = controladorCoche.buscarPorMatricula("1123HAR");
        assertNull(cocheOriginal, "El coche con la matrícula '1123HAR' debería haber sido actualizado y no existir ya.");
    }

    @Test
    void borrarCoche() throws SQLException {
        Coche coche1 = new Coche("1123HAR", "Ford", "Focus", new Date());
        Coche coche2 = new Coche("999HAR", "Opel", "Moka", new Date());
        controladorCoche.guardarCoche(coche1);
        controladorCoche.guardarCoche(coche2);
        controladorCoche.eliminarCoche(coche1);
    }

    @Test
    void quitarCocheCliente() throws SQLException {
        String dni = "12345678A";
        String nombre = "Juan";
        String apellido = "Pérez";
        String telefono = "123456789";
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        controladorCliente.registrarCliente(cliente);
        Coche coche1 = new Coche("1123HAR", "Ford", "Fiesta", new Date(), cliente);
        Coche coche2 = new Coche("999HAR", "Opel", "Corsa", new Date(), cliente);
        controladorCoche.guardarCoche(coche1);
        controladorCoche.guardarCoche(coche2);
        controladorCoche.eliminarCoche(coche1);
    }



}