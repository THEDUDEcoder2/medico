package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DoctorServices {
    private EntityManagerFactory entityManagerFactory;
    public DoctorServices() {
        entityManagerFactory = HibernateUtils.getEntityManagerFactory();
    }

    public void addDoctor(Doctor doctor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(doctor);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Doctor> getAllDoctors() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Doctor> query = entityManager.createQuery("FROM Doctor", Doctor.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Doctor getDoctorByID(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Doctor.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void updateDoctor(Doctor doctor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(doctor);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void removeDoctor(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Doctor doctor = entityManager.find(Doctor.class, id);
            if (doctor != null) {
                entityManager.remove(doctor);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}