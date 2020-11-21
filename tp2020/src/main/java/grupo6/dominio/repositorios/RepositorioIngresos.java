package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RepositorioIngresos extends Repositorio<OperacionDeIngreso> {

    private EntityManagerFactory entityManagerFactory;
    private static RepositorioIngresos yoMismo = null;

    public static RepositorioIngresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioIngresos(new DAOHibernate<>(OperacionDeIngreso.class));
        }
        return yoMismo;
    }

    public ArrayList<OperacionDeIngreso> obtenerTodos(){
        return (ArrayList<OperacionDeIngreso>) this.dao.buscarTodos();
    }

    public OperacionDeIngreso buscar(int id){
//        return this.ingresos.stream().filter(e -> e.getId() == id).findFirst().get();
        return this.dao.buscar(id);
    }

    private RepositorioIngresos(DAO<OperacionDeIngreso> dao) {
        super(dao);
    }

    public void agregar(OperacionDeIngreso i){
        this.dao.agregar(i);
    }

    public void eliminar(OperacionDeIngreso i){
        this.dao.eliminar(i);
    }

    public List<OperacionDeIngreso> obtenerTodos(List<String> criterios, List<String> categorias){
        //return this.egresos.stream().filter(e -> e.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
//        return this.dao.buscarTodos(condicionCriteriosYCategorias(criterios, categorias));
        // Generamos el filtro en memoria. No es la mejor manera pero es muy complicado armarlo en query
        List<OperacionDeIngreso> listaIngresos = this.dao.buscarTodos();
        return listaIngresos.stream().filter(i -> i.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
    }


    public void cargarDatosTest() {
        //INSTANCIO OPERACIONES DE INGRESO
        OperacionDeIngreso i1, i2, i3;
        Categoria donacion = RepositorioCriterios.getInstancia().buscar("TipoIngreso").buscar("donacion");
        i1 = new OperacionDeIngreso("Donacion 1", 3500d, LocalDate.parse("2018-02-27"));
        i1.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i1.agregarCategoria(donacion);
        i2 = new OperacionDeIngreso("Donacion 2", 4000d, LocalDate.parse("2018-02-22"));
        i2.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i2.agregarCategoria(donacion);
        i3 = new OperacionDeIngreso("Operacion 3", 2500d, LocalDate.parse("2018-02-23"));
        i3.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));

        this.agregar(i1);
        this.agregar(i2);
        this.agregar(i3);
    }

}
