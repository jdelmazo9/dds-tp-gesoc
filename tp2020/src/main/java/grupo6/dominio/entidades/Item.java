package grupo6.dominio.entidades;

import java.util.Objects;

public class Item {
    // Hardcodeo in id para boton de borrar
    private static int cant_items = 0;
    private int id;
    private TipoItem tipo;
    private String descripcion;
    private Double valor;

    public Item(TipoItem tipo, String descripcion, Double valor) {
        this.id = cant_items;
        cant_items += 1;
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

	public Integer getId() {
		return this.id;
	}
}
