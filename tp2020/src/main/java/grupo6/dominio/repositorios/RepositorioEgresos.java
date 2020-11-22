package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioEgresos extends Repositorio<OperacionDeEgreso> {

    private static RepositorioEgresos yoMismo = null;

    private RepositorioEgresos(DAO<OperacionDeEgreso> dao) {
        super(dao);
    }

//    private ArrayList<OperacionDeEgreso> egresos ;

    public static RepositorioEgresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioEgresos(new DAOHibernate<>(OperacionDeEgreso.class));
        }
        return yoMismo;
    }

    public void agregar(OperacionDeEgreso e){
        this.dao.agregar(e);
    }

    public ArrayList<OperacionDeEgreso> obtenerTodos(){
        return (ArrayList<OperacionDeEgreso>) this.dao.buscarTodos();
    }

    public List<OperacionDeEgreso> obtenerTodos(List<String> criterios, List<String> categorias){
        //return this.egresos.stream().filter(e -> e.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
//        return this.dao.buscarTodos(condicionCriteriosYCategorias(criterios, categorias));
        // Generamos el filtro en memoria. No es la mejor manera pero es muy complicado armarlo en query
        List<OperacionDeEgreso> listaEgresos = this.dao.buscarTodos();
        return listaEgresos.stream().filter(e -> e.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
    }

//    BusquedaCondicional condicionCriteriosYCategorias(List<String> criterios, List<String> categorias) {
//        CriteriaBuilder criteriaBuilder = criteriaBuilder();
//        CriteriaQuery<OperacionDeEgreso> cumpleCatQuery = criteriaBuilder.createQuery(OperacionDeEgreso.class);
//        Root<OperacionDeEgreso> condicionRaiz = cumpleCatQuery.from(OperacionDeEgreso.class);
//        Predicate condicionDeCategorias = criteriaBuilder.isTrue(condicionRaiz.get
//            condicionRaiz.get("nombre"), nombreCriterio);
//        criterioQuery.where(condicionNombreDeCriterio);
//        return new BusquedaCondicional(null, criterioQuery);
//
//    }

    public void eliminar(OperacionDeEgreso e){
        this.dao.eliminar(e);
    }

    public OperacionDeEgreso buscar(int id){
        System.out.println("Deberia estar buscando");
        //return this.egresos.stream().filter(e -> e.getId() == id).findFirst().get();
        return this.dao.buscar(id);
    }

}
