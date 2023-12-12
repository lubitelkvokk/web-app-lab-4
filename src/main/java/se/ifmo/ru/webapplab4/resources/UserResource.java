package se.ifmo.ru.webapplab4.resources;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.UserException;
import se.ifmo.ru.webapplab4.models.UserModel;
import se.ifmo.ru.webapplab4.services.UserService;
import se.ifmo.ru.webapplab4.util.JwtManager;

import java.io.Serializable;

@Path("/user")
@SessionScoped
public class UserResource implements Serializable {

    @Inject
    private UserService userService;


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserModel user) throws UserException {
        System.out.println(user.getLogin() + " " + user.getPassword());
        userService.registerUser(user);
        return Response.ok("Succesfully registration")
                .build();
    }


    @POST
    public Response authenticate(UserModel user) throws UserException {
        return userService.authenticateUser(user);
    }

    @PostConstruct
    public void logging() {
        System.out.println("UserResource was constructed");
    }
}