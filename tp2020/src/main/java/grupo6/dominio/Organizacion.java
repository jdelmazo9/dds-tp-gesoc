package grupo6.dominio;

import java.util.ArrayList;

public class Organizacion {
    private ArrayList<EntidadJuridica> entidadesJuridicas;

    public Organizacion (){
        entidadesJuridicas = new ArrayList<EntidadJuridica>();
    };

    public Organizacion (ArrayList<EntidadJuridica> lista){
        entidadesJuridicas = lista;
    };

    public ArrayList<EntidadJuridica> getEntidadesJuridicas() {
        return entidadesJuridicas;
    }

    public void setEntidadesJuridicas(ArrayList<EntidadJuridica> entidadesJuridicas) {
        this.entidadesJuridicas = entidadesJuridicas;
    }

    //SIRVE LA LISTA O ES MEJOR UN DICCIONARIO?
}
