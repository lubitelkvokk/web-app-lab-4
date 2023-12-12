package se.ifmo.ru.webapplab4.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import se.ifmo.ru.webapplab4.dao.UserDao;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.LoginException;
import se.ifmo.ru.webapplab4.exception.UserException;
import se.ifmo.ru.webapplab4.models.UserConverter;
import se.ifmo.ru.webapplab4.models.UserModel;
import se.ifmo.ru.webapplab4.util.JwtManager;
import se.ifmo.ru.webapplab4.util.PasswordInteraction;
import se.ifmo.ru.webapplab4.util.validation.UserValidation;

@Named
@RequestScoped
public class UserService {

    @Inject
    UserDao userDaoImpl;

    @Inject
    UserValidation userValidation;

    @Inject
    private PasswordInteraction passwordInteraction;

    @Inject
    UserConverter userConverter;

    @Inject
    private JwtManager jwtManager;

    public void registerUser(UserModel user) throws UserException {
        userValidation.validateUser(user);
        try {
            userDaoImpl.registerUser(userConverter.convertUserModelToEntity(user));
        } catch (ConstraintViolationException e){
            throw new UserException("The login is already taken.");
        }

    }

    public Response authenticateUser(UserModel user) throws UserException {
        userValidation.validateUser(user);
        try {
            UserEntity verifiedUser = userDaoImpl.findUserByLogin(user.getLogin());
            if (passwordInteraction.verifyPassword(user.getPassword(), verifiedUser.getToken())) {
                return Response.ok(jwtManager.createToken(user.getLogin()))
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Wrong password")
                        .build();
            }
        } catch (NoResultException e) {
            throw new LoginException("There isn't such user");
        }

    }


}
