package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Pacientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Pacientesservices {
    public void Pacientesservice() {

    }

    public void addUser(Pacientes pacientes) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(pacientes);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Pacientes> getAllUsers() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Pacientes> result = entityManager.createQuery( "from Usuario", Pacientes.class ).getResultList();
        entityManager.close();
        return result;
    }

    public Pacientes getUserByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Pacientes pacientes = entityManager.find(Pacientes.class, id);
        return pacientes;
    }

    public void updateUser(Pacientes pacientes) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(pacientes);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void removeUse(Pacientes pacientes) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(pacientes));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


