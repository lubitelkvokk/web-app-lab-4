package se.ifmo.ru.webapplab4.util;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;

@Named
@ApplicationScoped
public class EntityManagerFactoryBean{

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        try {
            if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
                entityManagerFactory = Persistence.createEntityManagerFactory("default");
                System.out.println("EntityManagerFactory was constructed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entityManagerFactory;
    }


//    @PostConstruct
//    public void init(){
//
//    }

    @PreDestroy
    public void destroy(){
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory was closed");
        }
    }


}
