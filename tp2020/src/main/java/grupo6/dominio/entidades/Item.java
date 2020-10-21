package grupo6.dominio.entidades;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private String descripcion;
    private Double valor;


    @Any(metaColumn = @Column(name = "documentoItemTipo"))
    @AnyMetaDef(idType = "int", metaType = "string",

        metaValues = {

        @MetaValue(targetEntity = OperacionDeEgreso.class, value = "Egreso"),

        @MetaValue(targetEntity = Presupuesto.class, value = "Presupuesto")

    })
//    @Cascade( { org.hibernate.annotations.CascadeType.ALL})
//
    @JoinColumn(name = "documentoItemID")

    private DocumentoItems documentoItem;


    public Item(TipoItem tipo, String descripcion, Double valor) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return tipo == item.tipo &&
            Objects.equals(descripcion, item.descripcion) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, descripcion);
    }
}
