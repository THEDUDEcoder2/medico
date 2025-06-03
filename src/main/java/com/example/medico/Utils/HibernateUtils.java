package com.example.medico.Utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtils {
    private static EntityManagerFactory entityManagerFactory = null;

    public HibernateUtils() {

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null)
            entityManagerFactory = Persistence.createEntityManagerFactory( "com.ites.proyectotemplate" );
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null)
            entityManagerFactory.close();
    }

}
