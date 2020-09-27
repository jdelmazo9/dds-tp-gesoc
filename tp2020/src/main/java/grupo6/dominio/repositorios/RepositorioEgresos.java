package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.OperacionDeEgreso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<OperacionDeEgreso> obtenerTodos(String criterio, String categoria){
        return this.egresos.stream().filter(e -> e.esDeCategoria(criterio, categoria)).collect(Collectors.toList());
    }

    public void eliminar(OperacionDeEgreso e){
        this.egresos.remove(e);
    }

    public OperacionDeEgreso buscar(int id){
        return this.egresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }
}
