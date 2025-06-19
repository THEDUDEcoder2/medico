package com.example.medico.services;

import com.example.medico.Utils.HibernateUtils;
import com.example.medico.modelos.Consulta;
import com.example.medico.modelos.Pacientes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class  ConsultaServices {
    private EntityManagerFactory entityManagerFactory;

    public ConsultaServices() {
        entityManagerFactory = HibernateUtils.getEntityManagerFactory();
    }

    public void addConsulta(Consulta consulta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(consulta);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Consulta> getAllConsultas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Consulta> query = entityManager.createQuery("FROM Consulta", Consulta.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Consulta getConsultaById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Consulta.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void updateConsulta(ConsultaServices consulta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(consulta);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void removeConsulta(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Consulta consulta = entityManager.find(Consulta.class, id);
            if (consulta != null) {
                entityManager.remove(consulta);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void agregarPacienteAConsulta(Long consultaId, Long pacienteId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Consulta consulta = entityManager.find(Consulta.class, consultaId);
            Pacientes paciente = entityManager.find(Pacientes.class, pacienteId);

            if (consulta != null && paciente != null) {
                consulta.getPacientes().add(paciente);
                paciente.getConsultas().add(consulta);
            }

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}


