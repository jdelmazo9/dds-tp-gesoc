package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.entidades.OperacionDeIngreso;

import java.util.ArrayList;
import java.util.List;

public class RepositorioIngresos {
    private List<OperacionDeIngreso> ingresos ;

    public RepositorioIngresos(){
        this.ingresos = new ArrayList<>();
    }

    public void agregar(OperacionDeIngreso e){
        this.ingresos.add(e);
    }

    public List<OperacionDeIngreso> obtenerTodos(){
        return this.ingresos;
    }

    public void eliminar(OperacionDeIngreso e){
        this.ingresos.remove(e);
    }

    public OperacionDeIngreso buscar(int id){
        return this.ingresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }

}
