package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Consulta;
import com.example.medico.modelos.Pacientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class Pacientesservices {
    private EntityManagerFactory entityManagerFactory;

    public Pacientesservices() {
        entityManagerFactory = HibernateUtils.getEntityManagerFactory();
    }

    public void addpaciente(Pacientes paciente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(paciente);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Pacientes> getAllpacientes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Pacientes> query = entityManager.createQuery("from pacientes", Pacientes.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Pacientes getpacienteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Pacientes.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void updatepaciente(Pacientes paciente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(paciente);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void removepaciente(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Pacientes paciente = entityManager.find(Pacientes.class, id);
            if (paciente != null) {
                entityManager.remove(paciente);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Consulta> getConsultasDePaciente(Long pacienteId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Pacientes paciente = entityManager.find(Pacientes.class, pacienteId);
            if (paciente != null) {
                return List.copyOf(paciente.getConsultas());
            }
            return List.of();
        } finally {
            entityManager.close();
        }
    }
}

