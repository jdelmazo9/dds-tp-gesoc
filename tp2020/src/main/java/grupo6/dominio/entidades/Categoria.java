package grupo6.dominio.entidades;


import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    @Hidden
//    @ManyToOne(cascade = CascadeType.MERGE)
    @ManyToOne
//    @JoinColumn(name = "CriterioID", referencedColumnName = "id")
    private Criterio criterio;

    public Categoria() {}

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(String nombre, Criterio criterio) {
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
