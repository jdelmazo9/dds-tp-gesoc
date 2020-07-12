package grupo6.utils;

import java.util.ArrayList;

import grupo6.dominio.ResultadoValidacion;

public class NotificadorValidadorLicitacion {
    private ArrayList<BandejaDeMensajes> bandejasRevisores;

    public NotificadorValidadorLicitacion(){
        bandejasRevisores = new ArrayList<BandejaDeMensajes>();
    }

    public void agregarRevisor(BandejaDeMensajes bandejaDeMensajes){
        bandejasRevisores.add(bandejaDeMensajes);
    }

    //Hacemos un notificar simple para validar esta parte, hay que agregar el notificarResultado del diagrama
    public void notificar(ResultadoValidacion resultado){
        bandejasRevisores.forEach(b -> b.agregarMensaje(resultado));
    }
}
