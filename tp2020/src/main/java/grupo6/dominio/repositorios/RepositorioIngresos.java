package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.CriterioAceptacion;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.TipoCriterio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;



public class RepositorioIngresos {
    private ArrayList<OperacionDeIngreso> ingresos ;
    private EntityManagerFactory entityManagerFactory;
    private static RepositorioIngresos yoMismo = null;

    public static RepositorioIngresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioIngresos();
        }
        return yoMismo;
    }



    public ArrayList<OperacionDeIngreso> obtenerTodos(){
        return this.ingresos;
    }

    public OperacionDeIngreso buscar(int id){
        return this.ingresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }


    private RepositorioIngresos(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("db");
        this.ingresos = new ArrayList<>();
    }

    public void agregar(OperacionDeIngreso i){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        this.ingresos.add(i);
        entityManager.getTransaction().begin();
        entityManager.persist(i);
        entityManager.getTransaction().commit();
    }

    public void eliminar(OperacionDeIngreso i){
        this.ingresos.remove(i);
    }

    public void cargarDatosTest() {
        //INSTANCIO OPERACIONES DE INGRESO
        OperacionDeIngreso i1, i2, i3;
        i1 = new OperacionDeIngreso("Operacion 1", 3500d, LocalDate.parse("2018-02-27"));
        i1.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i2 = new OperacionDeIngreso("Operacion 2", 4000d, LocalDate.parse("2018-02-22"));
        i2.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i3 = new OperacionDeIngreso("Operacion 3", 2500d, LocalDate.parse("2018-02-23"));
        i3.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));

        agregar(i1);
        agregar(i2);
        agregar(i3);
    }

}
