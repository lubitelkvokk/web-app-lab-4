package se.ifmo.ru.webapplab4.util;


import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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



    @PreDestroy
    public void destroy(){
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory was closed");
        }
    }


}
