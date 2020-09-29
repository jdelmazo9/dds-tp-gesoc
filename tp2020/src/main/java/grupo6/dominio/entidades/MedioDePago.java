package grupo6.dominio.entidades;

public class MedioDePago {
    private TipoPago medio;
    private int numeroId;

    public MedioDePago(TipoPago medio, int numeroId) {
        this.medio = medio;
        this.numeroId = numeroId;
    }

    public TipoPago getMedio() {
        return medio;
    }

    public void setMedio(TipoPago medio) {
        this.medio = medio;
    }

    public int getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(int numeroId) {
        this.numeroId = numeroId;
    }
}
