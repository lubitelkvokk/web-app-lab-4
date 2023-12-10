package se.ifmo.ru.webapplab4.resources;

import jakarta.annotation.PostConstruct;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
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
    public Response hello(UserEntity user) throws UserException {
        System.out.println(user.getLogin() + " " + user.getPassword());
        userService.registerUser(user);
        return Response.ok("Succesfully registration").build();
    }


    @POST
    public Response loginUser(UserEntity user) {
        System.out.println(user.getLogin() + " " + user.getPassword());
        userService.findUser();
        return Response.ok("ABOBAAAA").build();
//        return "while we don't find such user";
    }

//    @POST
//    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
//    public Response authenticate(UserEntity user) throws UserException {
//        // Логика аутентификации пользователя
//        boolean isAuthenticated = userService.authenticateUser(user);
//        if (isAuthenticated) {
//            return Response.ok("Authentication successful!")
////                    .header("Access-Control-Allow-Origin", "*")
//                    .build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .header("Access-Control-Allow-Origin", "*")
//                    .build();
//        }
//    }

    @PostConstruct
    public void logging() {
        System.out.println("UserResource was constructed");
    }
}