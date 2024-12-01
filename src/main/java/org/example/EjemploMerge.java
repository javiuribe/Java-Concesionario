package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EjemploMerge {
    public static void main(String[] args) {
        // Configuración inicial de EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        // Crear un cliente y un coche
        Cliente cliente = new Cliente("11111Z", "Jose", "Gomez", "+34123123123");

        // Persistir el cliente
        entityTransaction.begin();
        entityManager.persist(cliente);  // Persistimos el cliente, lo que persiste también el coche
        entityTransaction.commit();

        // Ahora, creamos un nuevo coche fuera del contexto de persistencia
        Coche cocheNoGestionado = new Coche("888ZZZ", "Toyota", "Corolla", new Date());

        // Recuperamos un cliente del contexto de persistencia y modificamos su coche
        entityTransaction.begin();
        Cliente clienteRecuperado = entityManager.find(Cliente.class, 1);

        // cocheNoGestionado no está en el contexto de persistencia.
        clienteRecuperado.getCoches().add(cocheNoGestionado);
        entityManager.merge(clienteRecuperado);  // El merge no tiene efecto sobre coche
        entityTransaction.commit();

        // Verificación
        entityTransaction.begin();
        Cliente clienteFinal = entityManager.find(Cliente.class, 1);

        System.out.println("Coches: " + clienteFinal.getCoches());  // Coches contiene el último coche a null
        entityTransaction.commit();

        // Cerrar el EntityManager
        entityManager.close();
        entityManagerFactory.close();
    }
}