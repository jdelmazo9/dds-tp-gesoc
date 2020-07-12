package grupo6.dominio;

import java.util.ArrayList;

public class Criterio {
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

    public void agregarPadre(Criterio padre) {
        this.criterioPadre = padre;
        if(padre.getHijo() != this){
            padre.agregarHijo(this);
        }
    }

    public void agregarHijo(Criterio hijo) {
        this.criterioHijo = hijo;
        if(hijo.getPadre() != this){
            hijo.agregarPadre(this);
        }
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public Criterio getPadre(){return this.criterioPadre; }

    public Criterio getHijo(){return this.criterioHijo;}



}

