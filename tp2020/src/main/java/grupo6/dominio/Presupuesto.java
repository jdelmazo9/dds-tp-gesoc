package grupo6.dominio;

import java.util.ArrayList;

public class Presupuesto {
    private ArrayList<Item> items;
    private double valorTotal;
    private Proveedor proveedor;
    private ArrayList<Categoria> categorias;

    public Presupuesto(ArrayList<Item> items, Proveedor proveedor) {
        this.items = items;
        this.calcular_valor_total();
        this.proveedor = proveedor;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void calcular_valor_total(){
        this.valorTotal = items.stream().mapToDouble(Item::getValor).sum();
    }

    public double getValorTotal() {
        return valorTotal;
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

