package org.example.controlador.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.example.controlador.dao.ClienteDao;
import org.example.modelo.Cliente;

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
    public Cliente findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = entityManager.find(Cliente.class, id);
        entityManager.close();
        return cliente;
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
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
