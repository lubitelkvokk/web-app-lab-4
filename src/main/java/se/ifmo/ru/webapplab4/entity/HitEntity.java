package se.ifmo.ru.webapplab4.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "hit", schema = "public", catalog = "true_notes_db")
@Data
public class HitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "x")
    private Double x;
    @Basic
    @Column(name = "y")
    private Double y;
    @Basic
    @Column(name = "r")
    private Double r;
    @Basic
    @Column(name = "time")
    private Date time;
    @Basic
    @Column(name = "hitting")
    private Boolean hitting;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private UserEntity userByUserId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HitEntity hitEntity = (HitEntity) o;

        if (id != hitEntity.id) return false;

        if (x != null ? !x.equals(hitEntity.x) : hitEntity.x != null) return false;
        if (y != null ? !y.equals(hitEntity.y) : hitEntity.y != null) return false;
        if (r != null ? !r.equals(hitEntity.r) : hitEntity.r != null) return false;
        if (time != null ? !time.equals(hitEntity.time) : hitEntity.time != null) return false;
        if (hitting != null ? !hitting.equals(hitEntity.hitting) : hitEntity.hitting != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (r != null ? r.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (hitting != null ? hitting.hashCode() : 0);
        return result;
    }


}
