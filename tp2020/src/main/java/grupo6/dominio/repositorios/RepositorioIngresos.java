package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Categoria;
import grupo6.dominio.entidades.CriterioAceptacion;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.TipoCriterio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class RepositorioIngresos {
    private ArrayList<OperacionDeIngreso> ingresos ;

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

    public List<OperacionDeIngreso> obtenerTodos(List<String> criterios, List<String> categorias){
        return this.ingresos.stream().filter(e -> e.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
    }

    public OperacionDeIngreso buscar(int id){
        return this.ingresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }


    private RepositorioIngresos(){
        this.ingresos = new ArrayList<>();
    }

    public void agregar(OperacionDeIngreso i){
        this.ingresos.add(i);
    }

    public void eliminar(OperacionDeIngreso i){
        this.ingresos.remove(i);
    }

    public void cargarDatosTest() {
        //INSTANCIO OPERACIONES DE INGRESO
        OperacionDeIngreso i1, i2, i3;
        i1 = new OperacionDeIngreso("Operacion 1", 3500d, LocalDate.parse("2018-02-27"));
        i1.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i1.agregarCategoria(RepositorioCriterios.getInstancia().buscar("Provincia").buscar("Buenos Aires"));
        i2 = new OperacionDeIngreso("Operacion 2", 4000d, LocalDate.parse("2018-02-22"));
        i2.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i2.agregarCategoria(RepositorioCriterios.getInstancia().buscar("Provincia").buscar("Buenos Aires"));
        i3 = new OperacionDeIngreso("Operacion 3", 2500d, LocalDate.parse("2018-02-23"));
        i3.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i3.agregarCategoria(RepositorioCriterios.getInstancia().buscar("Provincia").buscar("Córdoba"));


        this.ingresos.add(i1);
        this.ingresos.add(i2);
        this.ingresos.add(i3);
    }

}
