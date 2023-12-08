package se.ifmo.ru.webapplab4.dao;

import se.ifmo.ru.webapplab4.entity.UserEntity;

public interface UserDao {

    public boolean registerUser(UserEntity user);

    public UserEntity findUser(UserEntity user);

    public boolean logInUser(UserEntity user);
}
