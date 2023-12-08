package se.ifmo.ru.webapplab4.resources;

import jakarta.annotation.PostConstruct;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.UserException;
import se.ifmo.ru.webapplab4.services.UserService;

import java.io.Serializable;

@Path("/user")
@SessionScoped
public class UserResource implements Serializable {

    @Inject
    private UserService userService;

    @GET
    public String findUser() {
        userService.findUser();
        return "while we don't find such user";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(UserEntity user) throws UserException {
        System.out.println(user.getLogin() + " " + user.getPassword());
        userService.registerUser(user);
        return "Hello, World!";
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String authenticate(UserEntity user) throws UserException {
        // Логика аутентификации пользователя
        boolean isAuthenticated = userService.authenticateUser(user);
        if (isAuthenticated) {
            return "Authentication successful!";
        } else {
            return "Authentication failed!";
        }
    }

    @PostConstruct
    public void logging() {
        System.out.println("UserResource was constructed");
    }
}