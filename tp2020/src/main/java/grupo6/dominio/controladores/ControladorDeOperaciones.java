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
        Integer pagina;

        if(request.queryParams("pagina") == null){
            pagina = 1;
        }
        else{
            pagina = new Integer(request.queryParams("pagina"));
        }

        response.type("application/json");
        String operaciones = null;
        if(entidad != null){
            if(TipoOperacion.valueOfLabel(tipo) != null){
                operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(entidad, TipoOperacion.valueOfLabel(tipo),pagina);
            }
            else
            operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(entidad, pagina);
        }
        else if(TipoOperacion.valueOfLabel(tipo) != null){
            operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(TipoOperacion.valueOfLabel(tipo), pagina);
        }
        else{
            operaciones = ServicioRegistroOperaciones.getInstancia().obtenerOperaciones(pagina);
        }
//        response.status(400);
        EntityManagerHelper.commit();
        return operaciones;
    }
}
