package grupo6.dominio.entidades;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import grupo6.spark.utils.NotificadorValidadorLicitacion;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OperacionDeEgreso")
public class OperacionDeEgreso extends DocumentoItems {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "documentoItemID")
    private List<DocumentoComercial> docsComerciales;

    private URL docComercialExterno;
    @ManyToOne
    private Proveedor proveedor;
    private LocalDate fecha;
    @ManyToOne
    private MedioDePago medioDePago;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="documentoItemID")
    private List<Item> items;
    @Transient
    private ArrayList<String> detalleItems;
    private Double valorTotal;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "operacionDeEgresoID")
    private List<Presupuesto> presupuestos;
    @ManyToMany
    private List<Categoria> categorias;

    // private ValidadorLicitacion validadorLicitacion
    @Transient
    private NotificadorValidadorLicitacion notificador;
    @Transient
    private ValidadorLicitacion validadorLicitacion;



    public OperacionDeEgreso() {
        docsComerciales = new ArrayList<DocumentoComercial>();
        items = new ArrayList<Item>();
        detalleItems = new ArrayList<String>();
        presupuestos = new ArrayList<Presupuesto>();
        fecha = LocalDate.now();
        notificador = new NotificadorValidadorLicitacion();
        validadorLicitacion = new ValidadorLicitacionMenorPrecio(); //Por ahora por ser el unico inicializamos uno
        //Calendar today = Calendar.getInstance();
        //fecha.getTime();
        categorias = new ArrayList<>();
    }

    //CONSTRUCTOR PARA TESTS
    public OperacionDeEgreso(LocalDate fecha) {
        docsComerciales = new ArrayList<DocumentoComercial>();
        items = new ArrayList<Item>();
        detalleItems = new ArrayList<String>();
        presupuestos = new ArrayList<Presupuesto>();
        this.fecha = fecha;
        notificador = new NotificadorValidadorLicitacion();
        validadorLicitacion = new ValidadorLicitacionMenorPrecio(); //Por ahora por ser el unico inicializamos uno
        //Calendar today = Calendar.getInstance();
        //fecha.getTime();
        categorias = new ArrayList<>();
    }

    public ArrayList<DocumentoComercial> getDocsComerciales() {
        return (ArrayList<DocumentoComercial>) docsComerciales;
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
        return fecha.toString();
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
        return (ArrayList<Item>) items;
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
        return (ArrayList<Categoria>) categorias;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Presupuesto> getPresupuestos() {
        return (ArrayList<Presupuesto>) presupuestos;
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

	public Item buscarItem(Integer entero) {
        for(Item i : this.items){
            if( i.getId() == (int)entero){
                System.out.println(i.getId().toString());
                return i;
            }
            
        }
        System.out.println("Estoy Fallando");
		return null;
	}

	public Presupuesto getPresupuesto(Integer entero) {
        for(Presupuesto p : this.presupuestos){
            if( p.getId() == (int)entero){
                return p;
            }
        }
        return null;
        
    }

	public Categoria getCategoria(Integer entero) {
        for(Categoria c : this.categorias){
            if( c.getId() == (int)entero){
                System.out.println(c.getId().toString());

                return c;
            }
        }
		return null;
	}

}


