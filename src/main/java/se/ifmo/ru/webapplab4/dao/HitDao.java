package se.ifmo.ru.webapplab4.dao;

import se.ifmo.ru.webapplab4.entity.HitEntity;
import se.ifmo.ru.webapplab4.entity.UserEntity;

import java.util.List;

public interface HitDao {

    List<HitEntity> getUserHits(UserEntity user);

    void addHit(HitEntity hit);

}
