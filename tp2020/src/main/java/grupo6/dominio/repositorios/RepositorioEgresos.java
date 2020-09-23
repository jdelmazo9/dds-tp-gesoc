package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.OperacionDeEgreso;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEgresos {
    private List<OperacionDeEgreso> egresos ;

    public RepositorioEgresos(){
        this.egresos = new ArrayList<>();
    }

    public void agregar(OperacionDeEgreso e){
        this.egresos.add(e);
    }

    public List<OperacionDeEgreso> obtenerTodos(){
        return this.egresos;
    }

    public void eliminar(OperacionDeEgreso e){
        this.egresos.remove(e);
    }

    public OperacionDeEgreso buscar(int id){
        return this.egresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }
}
