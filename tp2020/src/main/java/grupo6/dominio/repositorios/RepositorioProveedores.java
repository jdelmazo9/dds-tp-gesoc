package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProveedores {
    private List<Proveedor> proveedores ;
    private static RepositorioProveedores yoMismo = null;

    public static RepositorioProveedores getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioProveedores();
        }
        return yoMismo;
    }

    private RepositorioProveedores(){
        this.proveedores = new ArrayList<>();
    }

    public void agregar(Proveedor p){
        this.proveedores.add(p);
    }

    public List<Proveedor> obtenerTodos(){
        return this.proveedores;
    }

    public void eliminar(Proveedor p){
        this.proveedores.remove(p);
    }

    public Proveedor buscar(int id){
        return this.proveedores.stream().filter(p -> p.getNroIdentificacion() == id).findFirst().get();
    }

    public void cargarProveedoresTest(){
        this.proveedores.add(new Proveedor("Coto", 1, "Calle Falsa 123"));
        this.proveedores.add(new Proveedor("Disco", 2, "Siempre Muerta 69"));
        this.proveedores.add(new Proveedor("My Crosoft", 3, "Wachin Ton 433"));
    }
}
