package se.ifmo.ru.webapplab4.services;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.ifmo.ru.webapplab4.dao.HitDao;
import se.ifmo.ru.webapplab4.dao.UserDao;
import se.ifmo.ru.webapplab4.entity.HitEntity;
import se.ifmo.ru.webapplab4.exception.HitBoundaryException;

import java.util.LinkedList;
import java.util.List;

@Named
@RequestScoped
public class HitService {


    @Inject
    private HitDao hitDaoImpl;

    @Inject
    private UserDao userDaoImpl;

    public List<HitEntity> getUserHits(String username, Integer pageNumber, Integer pageSize) {
        return hitDaoImpl.getUserHits(username, pageNumber, pageSize);
    }

    public void addHitForUser(String username, HitEntity hitEntity) throws HitBoundaryException {
        hitEntity.setHitting(isHitting(hitEntity));
        hitEntity.setUserByUserId(userDaoImpl.findUserByLogin(username));
        hitDaoImpl.addHit(hitEntity);
    }

    private boolean isHitting(HitEntity hitEntity) throws HitBoundaryException {
        Double x = hitEntity.getX();
        Double y = hitEntity.getY();
        Double r = hitEntity.getR();

        System.out.println("X VALUE: " + x);
        System.out.println("Y VALUE: " + y);
        System.out.println("R VALUE: " + r);
        System.out.println(x < -3 || x > 5);
        if (x < -3 || x > 5) {
            throw new HitBoundaryException("value X must be between -3 and 5");
        } else if (y < -5 || y > 3) {
            throw new HitBoundaryException("value Y must be between -5 and 3");
        } else if (r < 0 || r > 5) {
            throw new HitBoundaryException("value R must be between 0 and 5");
        } else {
            if (x * x + y * y <= r * r / 4 && x <= 0 && y >= 0
                    || y <= (-x + r / 2) && x >= 0 && y >= 0
                    || x >= 0 && x <= r / 2 && y <= 0 && y >= -r) {
                return true;
            }
            return false;
        }
    }

}
