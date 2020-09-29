package grupo6.dominio.controladores;

import com.google.gson.Gson;
import grupo6.dominio.entidades.ResultadoValidacion;
import grupo6.spark.utils.BandejaDeMensajes;
import spark.Request;
import spark.Response;

import java.util.ArrayList;


public class ControladorDeValidaciones {

    private static ControladorDeValidaciones yoMismo = null;
    private BandejaDeMensajes bandejaDeMensajes = null;

    public static ControladorDeValidaciones getInstancia(){
        if(yoMismo == null){
            yoMismo = new ControladorDeValidaciones();
        }
        return yoMismo;
    }

    private ControladorDeValidaciones(){
        bandejaDeMensajes = new BandejaDeMensajes();
    }

    public BandejaDeMensajes getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public String obtenerValidaciones(Request request, Response response){
        Gson gson = new Gson();
        String json = gson.toJson(this.bandejaDeMensajes.obtenerMensajes());
        response.type("application/json");
        return json;
    }

    public String obtenerValidacionesEgreso(int idEgreso){
        Gson gson = new Gson();
        String json = gson.toJson(this.bandejaDeMensajes.obtenerMensajes(idEgreso));
        return json;
    }
}
