package org.example.controlador.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.controlador.dao.ClienteDao;
import org.example.modelo.Cliente;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaClienteDao implements ClienteDao {
    private final EntityManagerFactory entityManagerFactory;

    public JpaClienteDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public void save(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Optional<Cliente> findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = entityManager.find(Cliente.class, id);
        if (cliente != null) {
            Hibernate.initialize(cliente.getCoches());
        }
        entityManager.close();
        return Optional.ofNullable(cliente);
    }


    @Override
    public List<Cliente> findByModelo(String marca, String modelo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String jpql = "SELECT cli FROM Cliente cli JOIN cli.coches co WHERE co.marca = :marca AND co.modelo = :modelo";
        List<Cliente> clientes = entityManager.createQuery(jpql, Cliente.class)
                .setParameter("marca", marca)
                .setParameter("modelo", modelo)
                .getResultList();
        entityManager.close();
        return clientes;
    }

    @Override
    public void update(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        cliente = entityManager.merge(cliente);
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = entityManager.createQuery(
                            "SELECT c FROM Cliente c WHERE c.nombre = :nombre", Cliente.class)
                    .setParameter("nombre", nombre)
                    .getResultList();
        } catch (Exception e) {
            return clientes;
        } finally {
            entityManager.close();
        }
        return clientes;
    }

}
