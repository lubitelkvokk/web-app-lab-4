package se.ifmo.ru.webapplab4.util;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class EntityManagerFactoryBean {

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @PostConstruct
    public void init(){
        System.out.println("EntityManagerFactory was constructed");
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    @PreDestroy
    public void destroy(){
        entityManagerFactory.close();
    }

}
