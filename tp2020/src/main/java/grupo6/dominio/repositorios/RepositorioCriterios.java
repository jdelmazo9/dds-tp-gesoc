package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Categoria;
import grupo6.dominio.entidades.Criterio;
import grupo6.dominio.entidades.Proveedor;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;
import grupo6.seguridad.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCriterios extends Repositorio<Criterio> {
    private static RepositorioCriterios yoMismo = null;

    public RepositorioCriterios(DAO<Criterio> dao) {
        super(dao);
    }

    public static RepositorioCriterios getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioCriterios(new DAOHibernate<>(Criterio.class));
        }
        return yoMismo;
    }

    public void agregar(Criterio c){
        this.dao.agregar(c);
    }

    public List<Criterio> getCriterios(){
        return this.dao.buscarTodos();
    }

    public List<Criterio> obtenerTodos(){
        return this.dao.buscarTodos();
    }

    public void eliminar(Criterio c){
        this.dao.eliminar(c);
    }

    public Criterio buscar(String nombreCriterio){
        return this.dao.buscar(condicionNombreCriterio(nombreCriterio));
    }

    BusquedaCondicional condicionNombreCriterio(String nombreCriterio) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Criterio> criterioQuery = criteriaBuilder.createQuery(Criterio.class);
        Root<Criterio> condicionRaiz = criterioQuery.from(Criterio.class);
        Predicate condicionNombreDeCriterio = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombreCriterio); //TODO: ta chequeado que ahi en el get tengo que poner el nombre del campo?
        criterioQuery.where(condicionNombreDeCriterio);
        return new BusquedaCondicional(null, criterioQuery);
    }

    public Criterio getCriterio(String nombreCriterio){
        return this.dao.buscar(condicionNombreCriterio(nombreCriterio));
    }

    public Criterio getCriterio(int id){
        return this.dao.buscar(id);
    }

    public void cargarCriteriosTest(){
        //criterio TipoProveedor / Categorias: Nacional, Internacional
        Criterio criterioTipoProveedor = new Criterio();
        criterioTipoProveedor.setNombre("TipoProveedor");
        //criterioTipoProveedor.setId(0);
        new Categoria("Nacional", criterioTipoProveedor);
        new Categoria("Internacional", criterioTipoProveedor);
        this.agregar(criterioTipoProveedor);
        System.out.println("agregue los criterios");

        //criterio Provincia / Categorias: Buenos Aires, Entre Ríos, Córdoba
        Criterio criterioProvincia = new Criterio();
        criterioProvincia.setNombre("Provincia");
        //criterioProvincia.setId(1);
        new Categoria("Buenos Aires", criterioProvincia);
        new Categoria("Entre Ríos", criterioProvincia);
        new Categoria("Córdoba", criterioProvincia);
        this.agregar(criterioProvincia);

        Criterio criterioTipoIngreso = new Criterio();
        criterioTipoIngreso.setNombre("TipoIngreso");
        //criterioTipoProveedor.setId(0);
        new Categoria("donacion", criterioTipoIngreso);
        this.agregar(criterioTipoIngreso);

        System.out.println("listooooooooo");

        /*
        //criterio Preferente / Categorias: Si, No
        Criterio criterioTipoProveedor = new Criterio();
        criterioTipoProveedor.setNombre("TipoProveedor");
        new Categoria("Nacional", criterioTipoProveedor);
        new Categoria("Internacional", criterioTipoProveedor);
        this.criterios.add(criterioTipoProveedor);
         */
    }
}
