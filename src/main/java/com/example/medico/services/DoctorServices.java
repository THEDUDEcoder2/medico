package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DoctorServices {
    public DoctorServices() {

    }

    public void addDoctor(Doctor doctor ) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(doctor);
        entityManager.getTransaction().commit();
    }

    public List<DoctorServices> getAllDoctor() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<DoctorServices> result = entityManager.createQuery( "from Usuario", DoctorServices.class ).getResultList();
        entityManager.close();
        return result;
    }

    public DoctorServices getDoctorByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        DoctorServices doctor = entityManager.find(DoctorServices.class, id);
        return doctor;
    }

    public void updateDoctor(DoctorServices doctor) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(doctor);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void removeDoctor(DoctorServices doctor) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(doctor));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


