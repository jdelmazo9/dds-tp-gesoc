package grupo6.spark.utils;

import java.util.ArrayList;

import grupo6.dominio.entidades.BandejaDeMensajes;
import grupo6.dominio.entidades.ResultadoValidacion;

public class NotificadorValidadorLicitacion {
    private ArrayList<BandejaDeMensajes> bandejasRevisores;

    public NotificadorValidadorLicitacion(){
        bandejasRevisores = new ArrayList<BandejaDeMensajes>();
    }

    public void agregarRevisor(BandejaDeMensajes bandejaDeMensajes){
        if(!bandejasRevisores.contains(bandejaDeMensajes)) {
            bandejasRevisores.add(bandejaDeMensajes);
        }
    }

    //Hacemos un notificar simple para validar esta parte, hay que agregar el notificarResultado del diagrama
    public void notificar(ResultadoValidacion resultado){
        bandejasRevisores.forEach(b -> b.agregarMensaje(resultado));
    }
}
