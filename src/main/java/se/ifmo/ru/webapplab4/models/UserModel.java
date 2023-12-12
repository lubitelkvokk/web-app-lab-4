package se.ifmo.ru.webapplab4.models;

import lombok.Data;


@Data
public class UserModel {

    private String login;

    private String password;

    public UserModel() {
    }


}
