package grupo6.dominio.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Criterio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @OneToOne
    private Criterio criterioPadre;
    @OneToOne
    private Criterio criterioHijo;
    @OneToMany(mappedBy = "criterio",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> categorias;

    public Criterio() {
        categorias = new ArrayList<Categoria>();
    }

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
        if(categoria.getCriterio()==null){
            categoria.vincularCriterio(this);
        }
    }

    public int getId() {
        return id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPadre(Criterio padre) {
        this.criterioPadre = padre;
        if(padre.getHijo() != this){
            padre.agregarHijo(this);
        }
    }

    public void agregarHijo(Criterio hijo) {
        if(this.criterioHijo == null){
            criterioHijo = hijo;
        }
        if(hijo.getPadre() != this){
            hijo.agregarPadre(this);
        }
    }

    public List<Categoria> getCategorias() {
        return (List<Categoria>) categorias;
    }

    public Criterio getPadre(){return this.criterioPadre; }

    public Criterio getHijo(){return this.criterioHijo;}

    public void limpiarHijo(){ criterioHijo.criterioPadre = null;
        this.criterioHijo = null;
    }
    public Categoria buscar(String nombreCategoria){
        return this.categorias.stream().filter(c -> c.getNombre().equals(nombreCategoria)).findFirst().get();
    }
}

