package grupo6.utils;

import java.util.ArrayList;

public class NotificadorValidadorLicitacion {
    private ArrayList<BandejaDeMensajes> bandejasRevisores;

    public NotificadorValidadorLicitacion(){
        bandejasRevisores = new ArrayList<BandejaDeMensajes>();
    }

    public void agregarRevisor(BandejaDeMensajes bandejaDeMensajes){
        bandejasRevisores.add(bandejaDeMensajes);
    }

    //Hacemos un notificar simple para validar esta parte, hay que agregar el notificarResultado del diagrama
    public void notificar(String resultado){
        bandejasRevisores.forEach(b -> b.agregarMensaje(new Mensaje(resultado)));
    }
}
