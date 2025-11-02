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
public class EjemploRemoveJpa {
    public static void main(String[] args) {
        // Configuración inicial de EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        // Crear un cliente y un coche
        Cliente cliente = new Cliente("11111Z", "Jose", "Gomez", "+34123123123");
        Coche coche = new Coche("888ZZZ", "Toyota", "Corolla", new Date());
        cliente.addCoche(coche);

        // Persistir el cliente y coche
        entityTransaction.begin();
        entityManager.persist(cliente);  // Persistimos el cliente, lo que persiste también el coche
        entityTransaction.commit();

        entityTransaction.begin();
        cliente = entityManager.find(Cliente.class, 1);
        Coche cocheRecuperado = cliente.getCoches().getFirst(); // Obtiene el primer coche
        cliente.removeCoche(cocheRecuperado); // Elimina el coche de la colección
        entityTransaction.commit();

        // Cerrar el EntityManager
        entityManager.close();
        entityManagerFactory.close();
    }
}