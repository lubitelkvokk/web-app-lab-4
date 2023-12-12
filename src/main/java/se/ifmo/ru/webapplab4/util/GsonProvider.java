package se.ifmo.ru.webapplab4.util;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class GsonProvider {
    private Gson gson;
    public GsonProvider(){
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }




}
