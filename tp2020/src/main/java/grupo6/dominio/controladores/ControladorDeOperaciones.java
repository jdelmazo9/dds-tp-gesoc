package grupo6.dominio.controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo6.bitacoraOperaciones.ServicioRegistroOperaciones;
import grupo6.bitacoraOperaciones.TipoOperacion;
import grupo6.dominio.entidades.HiddenAnnotationExclusionStrategy;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.repositorios.RepositorioIngresos;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControladorDeOperaciones {
    public String obtenerOperaciones(Request request, Response response){

        String entidad = request.queryParams("entidad");
        String tipo = request.queryParams("tipo");

        System.out.println("TIPOOOOO: "+tipo);
        response.type("application/json");

        if(entidad != null){
            if(tipo != null){
                return ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(entidad, TipoOperacion.valueOfLabel(tipo));
            }
            return ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(entidad);
        }
        if(tipo != null){
            return ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(TipoOperacion.valueOfLabel(tipo));
        }
        response.status(400);
        return null;
    }
}
