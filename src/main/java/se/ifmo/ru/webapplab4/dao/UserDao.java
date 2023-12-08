package se.ifmo.ru.webapplab4.dao;

import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.LoginException;

public interface UserDao {

    public boolean registerUser(UserEntity user) throws LoginException;

    public UserEntity findUser(UserEntity user);


    public UserEntity findUserByLogin(String login);
}
