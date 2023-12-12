package se.ifmo.ru.webapplab4.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HitProjection {

    private int id;

    private Double x;

    private Double y;

    private Double r;

    private Date time;

    private Boolean hitting;

    private UserEntity userByUserId;


    public HitProjection(int id, Double x, Double y, Double r, Date time, Boolean hitting) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.time = time;
        this.hitting = hitting;
    }


}
