package grupo6.dominio.controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.EntityManagerHelper;
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
        EntityManagerHelper.beginTransaction();
        String entidad = request.queryParams("entidad");
        String tipo = request.queryParams("tipo");

        System.out.println("TIPOOOOO: "+tipo);
        response.type("application/json");
        String operaciones = null;
        if(entidad != null){
            if(tipo != null){
                operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(entidad, TipoOperacion.valueOfLabel(tipo));
            }
            else
            operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(entidad);
        }
        else if(tipo != null){
            operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(TipoOperacion.valueOfLabel(tipo));
        }
        response.status(400);
        EntityManagerHelper.commit();
        return operaciones;
    }
}
