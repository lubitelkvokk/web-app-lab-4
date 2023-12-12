package se.ifmo.ru.webapplab4.resources;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.webapplab4.entity.UserEntity;
import se.ifmo.ru.webapplab4.exception.UserException;
import se.ifmo.ru.webapplab4.services.UserService;
import se.ifmo.ru.webapplab4.util.JwtManager;

import java.io.Serializable;

@Path("/user")
@SessionScoped
public class UserResource implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private JwtManager jwtManager;


    @Deprecated
    @GET
    public String findUser(@HeaderParam("Authorization") String authToken) {
        System.out.println(authToken);
        userService.findUser();
        return "while we don't find such user";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserEntity user) throws UserException {
        System.out.println(user.getLogin() + " " + user.getPassword());
        userService.registerUser(user);
        return Response.ok("Succesfully registration")
                .build();
    }


    @POST
    public Response authenticate(UserEntity user) throws UserException {
        // Логика аутентификации пользователя
        boolean isAuth =  userService.authenticateUser(user);
        if (isAuth){
            return Response.ok(jwtManager.createToken(user.getLogin()))
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Wrong password")
                    .build();
        }

    }

    @PostConstruct
    public void logging() {
        System.out.println("UserResource was constructed");
    }
}