package se.ifmo.ru.webapplab4.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "user", schema = "public", catalog = "true_notes_db")
@Data
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "token")
    private String token;



    @Transient
    private String password;
//    @OneToMany(mappedBy = "userByUserId")
//    private Collection<HitEntity> hitsById;

    public UserEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

}
