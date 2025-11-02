package org.example.mainJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EjemploPersistJpa {
    public static void main(String[] args) {
        // Configuración inicial de EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        // Persistimos el cliente y su coche
        Cliente cliente = new Cliente("12345X", "Juan", "Pérez", "+34123456789");
        Coche coche = new Coche("888ZZZ", "Toyota", "Corolla", new Date());
        cliente.addCoche(coche);
        entityManager.persist(cliente);
        entityTransaction.commit(); // Cliente y coche ahora están en estado detached

        // Modificamos el coche fuera del contexto de persistencia
        coche.setModelo("Camry");

        // Intentamos sincronizar nuevamente la entidad con la base de datos
        entityTransaction.begin();
        entityManager.merge(cliente);  // Reincorpora el cliente y sus coches a estado attached
        entityTransaction.commit();

    }
}