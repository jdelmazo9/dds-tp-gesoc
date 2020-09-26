package grupo6.dominio.repositorios;

import grupo6.dominio.entidades.Categoria;
import grupo6.dominio.entidades.Criterio;
import grupo6.dominio.entidades.Proveedor;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.seguridad.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCriterios {
    private List<Criterio> criterios;
    private static RepositorioCriterios yoMismo = null;

    public static RepositorioCriterios getInstancia(){
        if(yoMismo == null){
            yoMismo = new RepositorioCriterios();
        }
        return yoMismo;
    }

    private RepositorioCriterios(){
        super();
        this.criterios = new ArrayList<>();
    }

    public void agregar(Criterio c){
        this.criterios.add(c);
    }

    public List<Criterio> getCriterios(){
        return this.criterios;
    }

    public List<Criterio> obtenerTodos(){
        return this.criterios;
    }

    public void eliminar(Criterio c){
        this.criterios.remove(c);
    }

    public Criterio buscar(String nombreCriterio){
        return this.criterios.stream().filter(c -> c.getNombre() == nombreCriterio).findFirst().get();
    }

    public Criterio getCriterio(String nombreCriterio){
        return this.criterios.stream().filter(c -> c.getNombre() == nombreCriterio).findFirst().get();
    }
    public Criterio getCriterio(int id){
        return this.criterios.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void cargarCriteriosTest(){
        //criterio TipoProveedor / Categorias: Nacional, Internacional
        Criterio criterioTipoProveedor = new Criterio();
        criterioTipoProveedor.setNombre("TipoProveedor");
        criterioTipoProveedor.setId(0);
        new Categoria("Nacional", criterioTipoProveedor);
        new Categoria("Internacional", criterioTipoProveedor);
        this.criterios.add(criterioTipoProveedor);

        //criterio Provincia / Categorias: Buenos Aires, Entre Ríos, Córdoba
        Criterio criterioProvincia = new Criterio();
        criterioProvincia.setNombre("Provincia");
        criterioProvincia.setId(1);
        new Categoria("Buenos Aires", criterioProvincia);
        new Categoria("Entre Ríos", criterioProvincia);
        new Categoria("Córdoba", criterioProvincia);
        this.criterios.add(criterioProvincia);

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
