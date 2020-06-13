package grupo6.utils;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeMensajes {
    private ArrayList<Mensaje> mensajes;

    public BandejaDeMensajes(){
        mensajes = new ArrayList<Mensaje>();
    }

    public void agregarMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    public List<Mensaje> obtenerMensajes(){
        return mensajes;
    }

    public List<Mensaje> obtenerPagina(int pagina, int mensajesPorPagina){
        return mensajes.subList(pagina*mensajesPorPagina, (pagina+1)*mensajesPorPagina - 1);
    }

    public int cantidadMensajes(){
        return mensajes.size();
    }

    public String leerPrimerMensaje() {
        return mensajes.get(0).leerMensaje();
    }
}
