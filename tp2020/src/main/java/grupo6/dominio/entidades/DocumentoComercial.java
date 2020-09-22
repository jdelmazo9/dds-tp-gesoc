package grupo6.dominio.entidades;

public class DocumentoComercial {
    private TipoDocumento tipo;
    private int numero;

    public DocumentoComercial(TipoDocumento tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
