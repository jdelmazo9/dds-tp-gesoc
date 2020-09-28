package grupo6.dominio.entidades;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo6.spark.utils.BandejaDeMensajes;
import grupo6.spark.utils.NotificadorValidadorLicitacion;

public class OperacionDeEgreso {
    static int operacionesCreadas = 0;
    private int id;
    private ArrayList<DocumentoComercial> docsComerciales;
    private URL docComercialExterno;
    private Proveedor proveedor;
    private LocalDate fecha;
    private String fechaStr;
    private MedioDePago medioDePago;
    private ArrayList<Item> items;
    private ArrayList<String> detalleItems;
    private Double valorTotal;
    private ArrayList<Presupuesto> presupuestos;

    private ArrayList<Categoria> categorias;

    // private ValidadorLicitacion validadorLicitacion

    private NotificadorValidadorLicitacion notificador;
    private ValidadorLicitacion validadorLicitacion;



    public OperacionDeEgreso() {
        id =  ++operacionesCreadas;
        docsComerciales = new ArrayList<DocumentoComercial>();
        items = new ArrayList<Item>();
        detalleItems = new ArrayList<String>();
        presupuestos = new ArrayList<Presupuesto>();
        fecha = LocalDate.now();
        fechaStr = fecha.toString();
        notificador = new NotificadorValidadorLicitacion();
        validadorLicitacion = new ValidadorLicitacionMenorPrecio(); //Por ahora por ser el unico inicializamos uno
        //Calendar today = Calendar.getInstance();
        //fecha.getTime();
        categorias = new ArrayList<>();
    }

    //CONSTRUCTOR PARA TESTS
    public OperacionDeEgreso(LocalDate fecha) {
        id =  ++operacionesCreadas;
        docsComerciales = new ArrayList<DocumentoComercial>();
        items = new ArrayList<Item>();
        detalleItems = new ArrayList<String>();
        presupuestos = new ArrayList<Presupuesto>();
        this.fecha = fecha;
        fechaStr = fecha.toString();
        notificador = new NotificadorValidadorLicitacion();
        validadorLicitacion = new ValidadorLicitacionMenorPrecio(); //Por ahora por ser el unico inicializamos uno
        //Calendar today = Calendar.getInstance();
        //fecha.getTime();
        categorias = new ArrayList<>();
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

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
        fechaStr = fecha.toString();
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

    public ArrayList<Categoria> getCategorias() {
        return categorias;
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

    public void agregarCategoria(Categoria categoria){this.categorias.add(categoria); }

    public void suscribirComoRevisor(BandejaDeMensajes bandeja){
        notificador.agregarRevisor(bandeja);
    }

    public void validarLicitacion(){
        ResultadoValidacion resultado = this.validadorLicitacion.validar(this);
        notificador.notificar(resultado);
    }

    public ValidadorLicitacion get_validador(){
        return this.validadorLicitacion;
    }

    public int getId() {
        return id;
    }

    public boolean esDeCategorias(List<String> criterios, List<String> categorias){

        for(int i = 0; i < categorias.size(); i++) {
            Boolean pertenece = false;
            for(Categoria c : this.categorias) {
                if(c.getNombre().equals(categorias.get(i)) && c.getCriterio().getNombre().equals(criterios.get(i))) {
                    pertenece = true;
                }
            }
            if(!pertenece) {
                return false;
            }
        }

        return true;
    }
}

