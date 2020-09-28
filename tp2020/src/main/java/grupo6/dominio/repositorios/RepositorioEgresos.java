package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.entidades.Proveedor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioEgresos {

    private static RepositorioEgresos yoMismo = null;

    public static RepositorioEgresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioEgresos();
        }
        return yoMismo;
    }

    private List<OperacionDeEgreso> egresos ;

    private RepositorioEgresos(){
        this.egresos = new ArrayList<>();
    }

    public void agregar(OperacionDeEgreso e){
        this.egresos.add(e);
    }

    public List<OperacionDeEgreso> obtenerTodos(){
        return this.egresos;
    }

    public List<OperacionDeEgreso> obtenerTodos(List<String> criterios, List<String> categorias){
        return this.egresos.stream().filter(e -> e.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
    }

    public void eliminar(OperacionDeEgreso e){
        this.egresos.remove(e);
    }

    public OperacionDeEgreso buscar(int id){
        return this.egresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }
}
