package se.ifmo.ru.webapplab4.models;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.util.PasswordInteraction;

@Named
@ApplicationScoped
public class UserConverter {

    @Inject
    private PasswordInteraction passwordInteraction;

    public UserEntity convertUserModelToEntity(UserModel userModel){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userModel.getLogin());
        userEntity.setToken(passwordInteraction.hashPassword(userModel.getPassword()));

        return userEntity;
    }

}
