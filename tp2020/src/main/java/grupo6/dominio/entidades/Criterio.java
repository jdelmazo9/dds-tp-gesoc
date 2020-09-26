package grupo6.dominio.entidades;

import java.util.ArrayList;

public class Criterio {
    int id;
    private String nombre;
    private Criterio criterioPadre;
    private Criterio criterioHijo;
    private ArrayList<Categoria> categorias;

    public Criterio() {
        categorias = new ArrayList<Categoria>();
    }

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
        if(categoria.getCriterio()==null){
            categoria.vincularCriterio(this);
        }
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public Criterio getPadre(){return this.criterioPadre; }

    public Criterio getHijo(){return this.criterioHijo;}

    public void limpiarHijo(){ criterioHijo.criterioPadre = null;
        this.criterioHijo = null;
    }
}

