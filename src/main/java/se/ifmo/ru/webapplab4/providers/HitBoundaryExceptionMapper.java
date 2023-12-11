package se.ifmo.ru.webapplab4.providers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.ru.webapplab4.exception.HitBoundaryException;
import se.ifmo.ru.webapplab4.exception.UserException;

@Provider
public class HitBoundaryExceptionMapper implements ExceptionMapper<HitBoundaryException> {
    @Override
    public Response toResponse(HitBoundaryException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }

}
