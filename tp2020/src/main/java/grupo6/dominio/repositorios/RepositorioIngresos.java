package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class RepositorioIngresos {
    private List<OperacionDeIngreso> ingresos ;
    private static RepositorioIngresos yoMismo = null;

    public static RepositorioIngresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioIngresos();
        }
        return yoMismo;
    }

    private RepositorioIngresos(){
        this.ingresos = new ArrayList<>();
    }

    public void agregar(OperacionDeIngreso i){
        this.ingresos.add(i);
    }

    public List<OperacionDeIngreso> obtenerTodos(){
        return this.ingresos;
    }

    public void eliminar(OperacionDeIngreso i){
        this.ingresos.remove(i);
    }

//    public Proveedor buscar(int id){
//        return this.proveedores.stream().filter(p -> p.getNroIdentificacion() == id).findFirst().get();
//    }

//    public void cargarProveedoresTest(){
//        this.proveedores.add(new Proveedor("Coto", 1, "Calle Falsa 123"));
//        this.proveedores.add(new Proveedor("Disco", 2, "Siempre Muerta 69"));
//        this.proveedores.add(new Proveedor("My Crosoft", 3, "Wachin Ton 433"));
//    }
}
