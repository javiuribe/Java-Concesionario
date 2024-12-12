package org.example.controlador.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.controlador.dao.ClienteDao;
import org.example.modelo.Cliente;
import org.hibernate.Hibernate;

import java.util.List;

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
        if (cliente != null) {
            Hibernate.initialize(cliente.getCoches());
        }
        entityManager.close();
        return cliente;
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Cliente> clientes = entityManager.createQuery(
                        "SELECT c FROM Cliente c WHERE c.nombre = :nombre", Cliente.class)
                .setParameter("nombre", nombre)
                .getResultList();
        clientes.forEach(c -> Hibernate.initialize(c.getCoches()));
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
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
