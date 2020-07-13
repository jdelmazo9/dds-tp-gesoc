package grupo6.dominio;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class OperacionDeEgreso {
    static int operacionesCreadas = 0;
    private int id;
    private ArrayList<DocumentoComercial> docsComerciales;
    private URL docComercialExterno;
    private Proveedor proveedor;
    private LocalDate fecha;
    private MedioDePago medioDePago;
    private ArrayList<Item> items;
    private ArrayList<String> detalleItems;
    private Double valorTotal;
    private ArrayList<Presupuesto> presupuestos;

    // private ValidadorLicitacion validadorLicitacion


    public OperacionDeEgreso() {
        id =  ++operacionesCreadas;
        docsComerciales = new ArrayList<DocumentoComercial>();
        items = new ArrayList<Item>();
        detalleItems = new ArrayList<String>();
        presupuestos = new ArrayList<Presupuesto>();
        fecha = LocalDate.now();
        //Calendar today = Calendar.getInstance();
        //fecha.getTime();
    }

    //CONSTRUCTOR PARA TESTS
    public OperacionDeEgreso(LocalDate fecha) {
        id =  ++operacionesCreadas;
        docsComerciales = new ArrayList<DocumentoComercial>();
        items = new ArrayList<Item>();
        detalleItems = new ArrayList<String>();
        presupuestos = new ArrayList<Presupuesto>();
        this.fecha = fecha;
        //Calendar today = Calendar.getInstance();
        //fecha.getTime();
    }

    public ArrayList<DocumentoComercial> getDocsComerciales() {
        return docsComerciales;
    }

    public void setDocsComerciales(ArrayList<DocumentoComercial> docsComerciales) {
        this.docsComerciales = docsComerciales;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public URL getDocComercialExterno() {
        return docComercialExterno;
    }

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void agregarItem(Item item){items.add(item);}

    public void eliminarItem(Item item){items.remove(item);}

    public ArrayList<String> getDetalleItems() {
        this.generarDetalle();
        return detalleItems;
    }

    public void setDetalleItems(ArrayList<String> detalleItems) {
        this.detalleItems = detalleItems;
    }

    public Double getValorTotal() {
        this.calcularCosto();
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(ArrayList<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }

    public void adjuntar(DocumentoComercial documento){this.docsComerciales.add(documento); }

    public void adjuntar(URL link){this.docComercialExterno = link;}

    public void calcularCosto(){
        this.valorTotal = items.stream().mapToDouble(Item::getValor).sum();
    }

    public void generarDetalle() { this.detalleItems = items.stream().map(Item::getDescripcion).collect(Collectors.toCollection(ArrayList::new)); }

    public void agregarPresupuesto(Presupuesto presupuesto){this.presupuestos.add(presupuesto);}

    public int getId() {
        return id;
    }
}

