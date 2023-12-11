package se.ifmo.ru.webapplab4.dao;

import se.ifmo.ru.webapplab4.entity.HitEntity;
import se.ifmo.ru.webapplab4.entity.UserEntity;

import java.util.List;

public interface HitDao {

    List<HitEntity> getUserHits(String username, Integer page_number, Integer page_size);

    void addHit(HitEntity hit);

}
