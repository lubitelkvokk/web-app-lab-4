package se.ifmo.ru.webapplab4.resources;

import com.google.gson.Gson;
import jakarta.enterprise.inject.Disposes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.weld.context.ejb.Ejb;
import se.ifmo.ru.webapplab4.entity.HitEntity;
import se.ifmo.ru.webapplab4.exception.HitBoundaryException;
import se.ifmo.ru.webapplab4.models.HitModel;
import se.ifmo.ru.webapplab4.services.HitService;
import se.ifmo.ru.webapplab4.util.GsonProvider;
import se.ifmo.ru.webapplab4.util.JwtManager;

import java.io.Serializable;
import java.util.List;

@Path("/hit")
public class HitResource implements Serializable {

    @Inject
    private JwtManager jwtManager;

    @Inject
    private HitService hitService;

    @Inject
    private GsonProvider gsonProvider;


    // TODO вынести логику помещения Authorization token в запрос
    @GET
    public Response getUserHits(@HeaderParam("Authorization") String authToken,
                                @QueryParam("pageNumber") Integer pageNumber,
                                @QueryParam("pageSize") Integer pageSize) {
        List<HitEntity> result = hitService.getUserHits(jwtManager.extractSubject(authToken), pageNumber, pageSize);
        return Response.ok(gsonProvider.getGson().toJson(result)).build();
    }

    @PUT
    public Response addHit(@HeaderParam("Authorization") String authToken,
                           HitModel hitModel) throws HitBoundaryException {
        hitService.addHitForUser(jwtManager.extractSubject(authToken), hitModel);
        return Response.ok().build();
    }

}
