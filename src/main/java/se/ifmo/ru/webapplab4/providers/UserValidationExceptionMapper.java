package se.ifmo.ru.webapplab4.providers;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.ru.webapplab4.exception.UserException;

@Provider
public class UserValidationExceptionMapper implements ExceptionMapper<UserException> {
    @Override
    public Response toResponse(UserException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("User data validation failed: " + e.getMessage())
                .build();
    }

}
