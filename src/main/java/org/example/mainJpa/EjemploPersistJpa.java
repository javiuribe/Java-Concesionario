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
        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.setApellidos("AAAAAA");
        entityTransaction.commit();

    }
}