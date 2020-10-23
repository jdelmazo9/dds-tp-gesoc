package grupo6.dominio.entidades;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class CriterioAceptacion {
    private HashMap<String, Object> parametros;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoCriterio tipoCriterio;

    public CriterioAceptacion() {}

    public CriterioAceptacion (TipoCriterio tipoCriterio) {
        this.tipoCriterio = tipoCriterio;
        parametros = new HashMap<String, Object>();
    }

    public void agregarParametros(String clave, Object valor) {
        this.parametros.put(clave, valor);
    }

    public TipoCriterio getTipoCriterio() {
        return tipoCriterio;
    }

    public Object getParametro(String key) {
        return parametros.get(key);
    }
}


