package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ConsultaServices {
    public ConsultaServices() {

    }

    public void addConsulta(Consulta consulta ) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(consulta);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<ConsultaServices> getAllConsulta() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ConsultaServices> result = entityManager.createQuery( "from Usuario", ConsultaServices.class ).getResultList();
        entityManager.close();
        return result;
    }

    public ConsultaServices getConsultaByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ConsultaServices consulta = entityManager.find(ConsultaServices.class, id);
        return consulta;
    }

    public void updateConsulta(ConsultaServices consulta) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(consulta);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void removeConsulta(ConsultaServices consulta) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(consulta));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}




