package se.ifmo.ru.webapplab4.implDao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
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
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return true;
    }


    @Override
    public UserEntity findUser(UserEntity user) {
        return null;
    }


    @Override
    public UserEntity findUserByLogin(String login) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.login = :login", UserEntity.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @PostConstruct
    public void logging() {
        System.out.println("UserDaoImpl was constructed");
    }

    @PreDestroy
    public void destroy() {

        entityManager.close();
    }
}
