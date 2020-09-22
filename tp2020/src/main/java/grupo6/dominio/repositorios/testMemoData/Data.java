package grupo6.dominio.repositorios.testMemoData;

import grupo6.dominio.entidades.EntidadPersistente;
import grupo6.seguridad.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<EntidadPersistente> getData(Class type){
        List<EntidadPersistente> entidades = new ArrayList<>();
        if(type.getName().equals(Usuario.class.getName())){
            entidades = DataUsuario.getList();
        }
        return entidades;
    }
}
