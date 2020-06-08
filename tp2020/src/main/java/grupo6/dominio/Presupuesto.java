package grupo6.dominio;

import java.util.ArrayList;

public class Presupuesto {
    private ArrayList<Item> items;
    private double valorTotal;
    private Proveedor proveedor;

    public Presupuesto(ArrayList<Item> items, double valorTotal, Proveedor proveedor) {
        this.items = items;
        this.valorTotal = valorTotal;
        this.proveedor = proveedor;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
}
