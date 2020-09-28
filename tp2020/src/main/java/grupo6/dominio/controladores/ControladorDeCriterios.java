package grupo6.dominio.controladores;


import com.google.gson.Gson;
import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.repositorios.RepositorioCriterios;
import spark.*;

public class ControladorDeCriterios {
    public String obtenerCriterio(Request request, Response response){
//        System.out.println(request.params("id"));
        Gson gson = new Gson();
        String nombre = RepositorioCriterios.getInstancia().getCriterio(request.params("id")).getNombre();
//        System.out.println(nombre);
//        System.out.println(RepositorioCriterios.getInstancia().getCriterio(Integer.parseInt(request.params("id"))).getNombre());
        String json = gson.toJson(RepositorioCriterios.getInstancia().getCriterio(request.params("id")));
        response.type("application/json");
       // System.out.println(json);
        return json;
    }
}
