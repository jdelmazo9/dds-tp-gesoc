package grupo6.dominio.entidades;

public class Item {
    private TipoItem tipo;
    private String descripcion;
    private Double valor;

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
}
