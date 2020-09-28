package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.OperacionDeIngreso;

import java.util.ArrayList;


public class RepositorioIngresos {
    private ArrayList<OperacionDeIngreso> ingresos ;
    private static RepositorioIngresos yoMismo = null;

    public static RepositorioIngresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioIngresos();
        }
        return yoMismo;
    }

    public RepositorioIngresos(){
        this.ingresos = new ArrayList<>();
    }

    public void agregar(OperacionDeIngreso e){
        this.ingresos.add(e);
    }

    public ArrayList<OperacionDeIngreso> obtenerTodos(){
        return this.ingresos;
    }

    public void eliminar(OperacionDeIngreso e){
        this.ingresos.remove(e);
    }

    public OperacionDeIngreso buscar(int id){
        return this.ingresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }
}
