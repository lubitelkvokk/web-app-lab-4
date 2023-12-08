package se.ifmo.ru.webapplab4.implDao;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import se.ifmo.ru.webapplab4.dao.UserDao;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.util.EntityManagerFactoryBean;

import java.io.Serializable;

@Named("userDaoImpl")
@RequestScoped
public class UserDaoImpl implements UserDao, Serializable {

    private final EntityManager entityManager;

    @Inject
    public UserDaoImpl(EntityManagerFactoryBean emfb) {
        entityManager = emfb.getEntityManagerFactory().createEntityManager();
    }


    @Override

    public boolean registerUser(UserEntity user) {
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // или замените на логирование
            return false;
        }
    }


    @Override
    public UserEntity findUser(UserEntity user) {
        return null;
    }

    @Override
    public boolean logInUser(UserEntity user) {
        return false;
    }

    @PostConstruct
    public void logging() {
        System.out.println("UserDaoImpl was constructed");
    }
}
