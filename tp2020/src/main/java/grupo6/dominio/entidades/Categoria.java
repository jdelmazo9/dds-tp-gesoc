package grupo6.dominio.entidades;

public class Categoria {
    private static int id_count = 0;
    private int id_categoria;
    private String nombre;
    private transient Criterio criterio;

    public Categoria(String nombre) { this.nombre = nombre; }

    public Categoria(String nombre, Criterio criterio) {
        this.id_categoria = id_count;
        id_count +=1;
        this.nombre = nombre;
        this.criterio = criterio;
        criterio.agregarCategoria(this);
    }


    public void vincularCriterio(Criterio criterio){
        if(this.getCriterio() == null){
        this.criterio = criterio;
        criterio.agregarCategoria(this); }}


    public String getNombre() { return nombre; }


    public Criterio getCriterio() { return criterio; }

    public void limpiar(){this.criterio = null;}

	public Integer getId() {
		return this.id_categoria;
	}

}
