package se.ifmo.ru.webapplab4.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.LoginException;
import se.ifmo.ru.webapplab4.exception.PasswordException;
import se.ifmo.ru.webapplab4.exception.UserException;
import se.ifmo.ru.webapplab4.implDao.UserDaoImpl;
import se.ifmo.ru.webapplab4.util.PasswordInteraction;
import se.ifmo.ru.webapplab4.util.validation.UserValidation;

import java.io.Serializable;

@Named
@RequestScoped
public class UserService {

    @Inject
    UserDaoImpl userDaoImpl;

    @Inject
    UserValidation userValidation;

    @Inject
    PasswordInteraction passwordInteraction;

    public void registerUser(UserEntity user) throws UserException {
        userValidation.validateUser(user);
        try{
            userDaoImpl.findUserByLogin(user.getLogin());
            throw new LoginException("This login is already taken.");
        } catch (NoResultException e) {
            user.setId(0);
            user.setToken(passwordInteraction.hashPassword(user.getPassword()));
            userDaoImpl.registerUser(user);
        }
    }

    public boolean authenticateUser(UserEntity user) throws UserException {
        userValidation.validateUser(user);
        try{
            UserEntity verifiedUser = userDaoImpl.findUserByLogin(user.getLogin());
            return passwordInteraction.verifyPassword(user.getPassword(), verifiedUser.getToken());
        } catch (NoResultException e) {
            throw new LoginException("There isn't such user");
        }

    }

    public UserEntity findUser() {
        return new UserEntity();
    }

}
