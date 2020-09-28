package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioEgresos {

    private static RepositorioEgresos yoMismo = null;

    private ArrayList<OperacionDeEgreso> egresos ;

    public static RepositorioEgresos getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioEgresos();
        }
        return yoMismo;
    }

    private RepositorioEgresos(){
        this.egresos = new ArrayList<>();
    }

    public void agregar(OperacionDeEgreso e){
        this.egresos.add(e);
    }

    public ArrayList<OperacionDeEgreso> obtenerTodos(){
        return this.egresos;
    }

    public List<OperacionDeEgreso> obtenerTodos(List<String> criterios, List<String> categorias){
        return this.egresos.stream().filter(e -> e.esDeCategorias(criterios, categorias)).collect(Collectors.toList());
    }

    public void eliminar(OperacionDeEgreso e){
        this.egresos.remove(e);
    }

    public OperacionDeEgreso buscar(int id){
        return this.egresos.stream().filter(e -> e.getId() == id).findFirst().get();
    }

    public void cargarDatosTest() {
        //INSTANCIO OPERACIONES DE INGRESO
        OperacionDeEgreso e1, e2, e3;
        e1 = new OperacionDeEgreso(LocalDate.parse("2018-03-28"));
        e2 = new OperacionDeEgreso(LocalDate.parse("2018-03-30"));
        e3 = new OperacionDeEgreso(LocalDate.parse("2018-03-29"));

        e1.agregarItem(new Item(TipoItem.Articulo, "Microondas marca Samsung", 1200.0));
        e2.agregarItem(new Item(TipoItem.Articulo, "Heladera marca Hp", 1700.00));
        e3.agregarItem(new Item(TipoItem.Articulo, "Lavarropas marca Hp", 1800.00));

        this.egresos.add(e1);
        this.egresos.add(e2);
        this.egresos.add(e3);
    }
}
