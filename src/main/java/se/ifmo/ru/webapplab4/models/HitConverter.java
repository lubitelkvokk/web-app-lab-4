package se.ifmo.ru.webapplab4.models;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import se.ifmo.ru.webapplab4.entity.HitEntity;

@Named
@ApplicationScoped
public class HitConverter {

    public HitEntity convertHitModelToEntity(HitModel hitModel){
        HitEntity hitEntity = new HitEntity();
        hitEntity.setX(hitModel.getX());
        hitEntity.setY(hitModel.getY());
        hitEntity.setR(hitModel.getR());
        hitEntity.setTime(hitModel.getTime());
        return hitEntity;
    }

}
