package grupo6.dominio.controladores;

import com.google.gson.Gson;
import db.EntityManagerHelper;
import grupo6.dominio.entidades.BandejaDeMensajes;
import spark.Request;
import spark.Response;


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
        EntityManagerHelper.beginTransaction();
        Gson gson = new Gson();
        String json = gson.toJson(this.bandejaDeMensajes.obtenerMensajes());
        response.type("application/json");
        EntityManagerHelper.commit();
        return json;
    }

    public String obtenerValidacionesEgreso(int idEgreso){
        EntityManagerHelper.beginTransaction();
        Gson gson = new Gson();
        String json = gson.toJson(this.bandejaDeMensajes.obtenerMensajes(idEgreso));
        EntityManagerHelper.commit();
        return json;
    }
}
