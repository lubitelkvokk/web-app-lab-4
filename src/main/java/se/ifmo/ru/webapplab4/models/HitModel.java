package se.ifmo.ru.webapplab4.models;

import lombok.Data;

import java.util.Date;

@Data
public class HitModel {

    private Double x;

    private Double y;

    private Double r;

    private Date time;

    private Boolean hitting;



}
