package grupo6.dominio.entidades;

import javax.persistence.*;

@Entity
public class DocumentoComercial {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
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
