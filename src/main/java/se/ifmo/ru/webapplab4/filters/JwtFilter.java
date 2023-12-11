package se.ifmo.ru.webapplab4.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import se.ifmo.ru.webapplab4.util.JwtManager;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class JwtFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    private JwtManager jwtManager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Проверяем путь запроса
        System.out.println(requestContext.getUriInfo().getPath());
        System.out.println(requestContext.getMethod());
        if ((requestContext.getMethod().equals("OPTIONS"))) {
            return;
        }

        if ("user".equals(requestContext.getUriInfo().getPath())
                && (requestContext.getMethod().equals("POST")
                || (requestContext.getMethod().equals("PUT")))) {
            // if it is authentication or registration we skip verifying token
        } else {
            if (!jwtManager.verifyToken(requestContext.getHeaderString("Authorization"))) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Token has expired. Please log in again or request a new token.")
                        .header("X-Auth-Update-Token", "Endpoint to refresh the token")
                        .header("X-Auth-Request-New-Token", "Endpoint to request a new token")
                        .build());
            }
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
    }
}

