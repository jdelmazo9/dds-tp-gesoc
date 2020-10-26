package grupo6.dominio.entidades;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity

public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private String descripcion;
    private Double valor;

    public Item() {}

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
