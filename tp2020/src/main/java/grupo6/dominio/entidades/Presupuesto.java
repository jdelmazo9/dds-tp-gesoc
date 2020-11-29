package grupo6.dominio.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Presupuesto")
public class Presupuesto extends DocumentoItems {


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="documentoItemID")
    private List<Item> items;
    private double valorTotal;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne
    private Proveedor proveedor;
    //@ManyToMany
    @Transient
    private List<Categoria> categorias;

    public Presupuesto(){}

    public Presupuesto(List<Item> items, Proveedor proveedor) {
        this.items = items;
        this.calcular_valor_total();
        this.proveedor = proveedor;
    }

    public List<Item> getItems() {
        return (List<Item>) items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void calcular_valor_total(){
        this.valorTotal = items.stream().mapToDouble(Item::getValor).sum();
    }

    public double getValorTotal() {
        this.calcular_valor_total();
        return this.valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void agregarCategoria(Categoria categoria) { this.categorias.add(categoria); }

}

