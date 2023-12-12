package se.ifmo.ru.webapplab4.implDao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import se.ifmo.ru.webapplab4.dao.HitDao;
import se.ifmo.ru.webapplab4.entity.HitEntity;
import se.ifmo.ru.webapplab4.util.EntityManagerFactoryBean;

import java.util.List;

@Named
@RequestScoped
public class HitDaoImpl implements HitDao {

    private final EntityManager entityManager;

    @Inject
    public HitDaoImpl(EntityManagerFactoryBean emfb) {
        entityManager = emfb.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public List getUserHits(String username, Integer pageNumber, Integer pageSize) {

        int startIndex = (pageNumber - 1) * pageSize;

        String queryStr = "SELECT new se.ifmo.ru.webapplab4.entity.HitProjection(h.id, h.x, h.y, h.r, h.time, h.hitting) from HitEntity h WHERE h.userByUserId.login = :username ORDER BY h.time";
        Query query =  entityManager.createQuery(queryStr);
        query.setParameter("username", username);
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public void addHit(HitEntity hit) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(hit);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
