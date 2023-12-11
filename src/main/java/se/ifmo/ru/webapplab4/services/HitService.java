package se.ifmo.ru.webapplab4.services;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.ifmo.ru.webapplab4.dao.HitDao;
import se.ifmo.ru.webapplab4.entity.HitEntity;

import java.util.LinkedList;
import java.util.List;

@Named
@RequestScoped
public class HitService {


    @Inject
    private HitDao hitDaoImpl;

    public List<HitEntity> getUserHits(String username, Integer pageNumber, Integer pageSize){
        return hitDaoImpl.getUserHits(username, pageNumber, pageSize);
    }


}
