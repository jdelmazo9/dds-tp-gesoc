package grupo6.dominio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    @Transient
    private transient Criterio criterio;

    public Categoria(String nombre) { this.nombre = nombre; }

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

}
