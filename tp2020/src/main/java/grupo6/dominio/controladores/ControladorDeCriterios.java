package grupo6.dominio.controladores;


import com.google.gson.*;
import db.EntityManagerHelper;
import grupo6.dominio.entidades.HiddenAnnotationExclusionStrategy;
import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.repositorios.RepositorioCriterios;
import spark.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class ControladorDeCriterios {
        public String obtenerCriterio(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies( new HiddenAnnotationExclusionStrategy() );
        Gson gson = builder.create();

        String json = gson.toJson(RepositorioCriterios.getInstancia().getCriterio(request.params("id")));
        response.type("application/json");
        EntityManagerHelper.commit();
        return json;
    }
}
