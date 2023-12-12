package se.ifmo.ru.webapplab4.util.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.LoginException;
import se.ifmo.ru.webapplab4.exception.PasswordException;
import se.ifmo.ru.webapplab4.models.UserModel;

@Named
@ApplicationScoped
public class UserValidation {

    public void validateUser(UserModel user) throws PasswordException, LoginException {
        if (user.getLogin() == null || user.getLogin().length() < 8) {
            throw new LoginException("Login must contain at least 8 symbol");
        } else if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new PasswordException("Password must contain at least 8 symbol");
        }
    }

}
