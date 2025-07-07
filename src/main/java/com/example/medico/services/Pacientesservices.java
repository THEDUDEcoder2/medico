package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Consulta;
import com.example.medico.modelos.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class Pacientesservices {
    private EntityManagerFactory entityManagerFactory;

    public Pacientesservices() {
        entityManagerFactory = HibernateUtils.getEntityManagerFactory();
    }

    public void addpaciente(Paciente paciente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(paciente);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Paciente> getAllpacientes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Paciente> query = entityManager.createQuery("FROM Paciente", Paciente.class); // Cambiado a "FROM Paciente"
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Paciente getpacienteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // aqui el cambio para que ahora si carguen las consultas junto al paciente
            TypedQuery<Paciente> query = entityManager.createQuery(
                    "SELECT p FROM Paciente p LEFT JOIN FETCH p.consultas WHERE p.id = :id",
                    Paciente.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public void updatepaciente(Paciente paciente) {
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
            Paciente paciente = entityManager.find(Paciente.class, id);
            if (paciente != null) {
                entityManager.remove(paciente);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void crearConsultaParaPaciente(Long pacienteId, Consulta consulta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Paciente paciente = entityManager.find(Paciente.class, pacienteId);
            if (paciente != null) {
                consulta.setPaciente(paciente);
                entityManager.persist(consulta);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}