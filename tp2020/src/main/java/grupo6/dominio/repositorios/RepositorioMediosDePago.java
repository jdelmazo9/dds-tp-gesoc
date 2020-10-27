package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Criterio;
import grupo6.dominio.entidades.MedioDePago;
import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;
import grupo6.dominio.servicios.AdapterMediosDePago;
import grupo6.dominio.servicios.AdapterMediosDePagoMP;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioMediosDePago extends Repositorio<MedioDePago> {

    private static RepositorioMediosDePago yoMismo = null;

    public static RepositorioMediosDePago getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioMediosDePago(new AdapterMediosDePagoMP(), new DAOHibernate<>(MedioDePago.class));
        }
        return yoMismo;
    }

    public RepositorioMediosDePago(AdapterMediosDePago adapterMediosDePago, DAO<MedioDePago> dao){
        super(dao);
        for(MedioDePago m: adapterMediosDePago.getMediosDePago())
            this.dao.agregar(m);
    }


    public MedioDePago buscar(String id){
        //return this.medioDePagos.stream().filter(e -> e.getId().equals(id)).findFirst().get();
        return this.dao.buscar(condicionMedioDePago(id));
    }

    BusquedaCondicional condicionMedioDePago(String id) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<MedioDePago> medioDePagoQuery = criteriaBuilder.createQuery(MedioDePago.class);
        Root<MedioDePago> condicionRaiz = medioDePagoQuery.from(MedioDePago.class);
        Predicate condicionMedioDePagoID = criteriaBuilder.equal(condicionRaiz.get("nombreID"), id); //TODO: ta chequeado que ahi en el get tengo que poner el nombre del campo?
        medioDePagoQuery.where(condicionMedioDePagoID);
        return new BusquedaCondicional(null, medioDePagoQuery);
    }

    public List<MedioDePago> getMedioDePagos() {
        return this.dao.buscarTodos();
    }
}
