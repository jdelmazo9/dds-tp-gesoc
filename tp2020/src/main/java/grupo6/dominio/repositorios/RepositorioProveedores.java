package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Criterio;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.Proveedor;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProveedores extends Repositorio<Proveedor> {

    private static RepositorioProveedores yoMismo = null;

    public static RepositorioProveedores getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioProveedores(new DAOHibernate<>(Proveedor.class));
        }
        return yoMismo;
    }

    private RepositorioProveedores(DAO<Proveedor> dao){
        super(dao);
    }

    public void agregar(Proveedor p){
        this.dao.agregar(p);
    }

    public List<Proveedor> obtenerTodos(){
        return this.dao.buscarTodos();
    }

    public void eliminar(Proveedor p){
        this.dao.eliminar(p);
    }

    public Proveedor buscar(int id){
        return this.dao.buscar(id);
    }

    public void cargarProveedoresTest(){
        this.agregar(new Proveedor("Coto", 1, "Calle Falsa 123"));
        this.agregar(new Proveedor("Disco", 2, "Siempre Muerta 69"));
        this.agregar(new Proveedor("My Crosoft", 3, "Wachin Ton 433"));
    }
}
