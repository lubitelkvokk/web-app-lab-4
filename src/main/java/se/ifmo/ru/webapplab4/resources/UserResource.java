package se.ifmo.ru.webapplab4.resources;

import jakarta.annotation.PostConstruct;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
    public String findUser(){
        userService.findUser();
        return "while we don't find such user";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(UserEntity user) throws UserException {
        System.out.println(user.getLogin() + " " + user.getPassword());
        userService.registerUser(user);
        return "Hello, World!";
    }


    @PostConstruct
    public void logging() {
        System.out.println("UserResource was constructed");
    }
}