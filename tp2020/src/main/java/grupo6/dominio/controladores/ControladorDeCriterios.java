package grupo6.dominio.controladores;


import com.google.gson.Gson;
import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.repositorios.RepositorioCriterios;
import spark.*;

public class ControladorDeCriterios {
    public String obtenerCriterio(Request request, Response response){
        Gson gson = new Gson();
        System.out.println(RepositorioCriterios.getInstancia().getCriterio(Integer.parseInt(request.params("id"))).getNombre());
        String json = gson.toJson(RepositorioCriterios.getInstancia().getCriterio(Integer.parseInt(request.params("id"))));
        response.type("application/json");
       // System.out.println(json);
        return "a";//json;
    }
}
