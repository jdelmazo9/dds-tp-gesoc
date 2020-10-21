package grupo6.dominio.entidades;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Presupuesto implements DocumentoItems {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="documentoItemID")
    @Where(clause="documentoItemTipo='Presupuesto'")
    private List<Item> items;
    private double valorTotal;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Proveedor proveedor;
    //@ManyToMany
    @Transient
    private ArrayList<Categoria> categorias;

    public Presupuesto(ArrayList<Item> items, Proveedor proveedor) {
        this.items = items;
        this.calcular_valor_total();
        this.proveedor = proveedor;
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) items;
    }

    public void setItems(ArrayList<Item> items) {
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

