package org.example.controlador.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.controlador.dao.CocheDao;
import org.example.modelo.Cliente;
import org.example.modelo.Coche;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import org.hibernate.Hibernate;

public class JpaCocheDao implements CocheDao {
    private final EntityManagerFactory entityManagerFactory;

    public JpaCocheDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public void save(Coche coche) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if (coche.getPropietario() != null) {
            coche.setPropietario(entityManager.merge(coche.getPropietario()));
        }
        entityManager.persist(coche);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public Optional<Coche> findByMatricula(String matricula) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Coche coche = entityManager.createQuery(
                        "SELECT c FROM Coche c WHERE c.matricula = :matricula", Coche.class)
                .setParameter("matricula", matricula)
                .getSingleResult();
        if (coche != null && coche.getPropietario() != null) {
            Hibernate.initialize(coche.getPropietario().getCoches());
        }
        return Optional.ofNullable(coche);
    }

    @Override
    public void update(Coche coche) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(coche);
        if (coche.getPropietario() != null) {
            entityManager.merge(coche.getPropietario());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Coche coche) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // Asegurarse de que el coche
        coche = entityManager.merge(coche);
        entityManager.remove(coche);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public List<Coche> findByPropietario(String dniPropietario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Coche> coches = entityManager.createQuery(
                        "SELECT c FROM Coche c WHERE c.propietario.dni = :dniPropietario", Coche.class)
                .setParameter("dniPropietario", dniPropietario)
                .getResultList();
        entityManager.close();
        return coches;
    }
}
