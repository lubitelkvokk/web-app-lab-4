package se.ifmo.ru.webapplab4.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.UserException;
import se.ifmo.ru.webapplab4.implDao.UserDaoImpl;
import se.ifmo.ru.webapplab4.util.validation.UserValidation;

@Named
@RequestScoped
public class UserService {

    @Inject
    UserDaoImpl userDaoImpl;

    @Inject
    UserValidation userValidation;

    public void registerUser(UserEntity user) throws UserException {
        userValidation.validateUser(user);
        // TODO need to do hashing a password to token
        user.setToken(user.getPassword());
        userDaoImpl.registerUser(user);
        // TODO AU DODIK VERNI NORMALNO
    }

    public UserEntity findUser(){
        return new UserEntity();
    }

}
