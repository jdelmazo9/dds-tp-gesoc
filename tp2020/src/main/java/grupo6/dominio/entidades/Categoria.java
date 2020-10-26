package grupo6.dominio.entidades;

import java.util.ArrayList;

public class Categoria {
    private static int id_count = 0;
    private int id;
    private String nombre;
    private transient Criterio criterio;

    public Categoria() {
        this.id = id_count;
        id_count +=1;
    }

    public Categoria(String nombre) {
        this.id = id_count;
        id_count +=1;
        this.nombre = nombre;
    }

    public Categoria(String nombre, Criterio criterio) {
        this.id = id_count;
        id_count +=1;
        this.nombre = nombre;
        this.criterio = criterio;
        criterio.agregarCategoria(this);
    }


    public void vincularCriterio(Criterio criterio){
        if(this.getCriterio() == null){
        this.criterio = criterio;
        criterio.agregarCategoria(this); }}


    public String getNombre() { return nombre; }


    public Criterio getCriterio() { return criterio; }

    public void limpiar(){this.criterio = null;}

	public Integer getId() {
		return this.id;
	}

}
