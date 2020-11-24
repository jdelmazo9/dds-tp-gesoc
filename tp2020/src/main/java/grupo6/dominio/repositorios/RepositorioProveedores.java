package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Criterio;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.Proveedor;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;
import grupo6.seguridad.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public Proveedor buscarProveedor(Integer nroIdentificacion) {
        Proveedor proveedor;
        try {
            proveedor = this.dao.buscar(condicionProveedor(nroIdentificacion));
        } catch (NoResultException e) {
            return null;
        }
        return proveedor;
    }

    private BusquedaCondicional condicionProveedor(Integer nroIdentificacion) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Proveedor> provQuery = criteriaBuilder.createQuery(Proveedor.class);

        Root<Proveedor> condicionRaiz = provQuery.from(Proveedor.class);

        Predicate predicate_numero = criteriaBuilder.equal(condicionRaiz.get("nroIdentificacion"), nroIdentificacion);

        provQuery.where(predicate_numero);

        return new BusquedaCondicional(null, provQuery);
    }
}
