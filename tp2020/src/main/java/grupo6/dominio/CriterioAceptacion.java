package grupo6.dominio;

import java.util.Map;

public class CriterioAceptacion {
    private Map<String, Object> parametros;
    private TipoCriterio tipoCriterio;

    public CriterioAceptacion (TipoCriterio tipoCriterio) {
        this.tipoCriterio = tipoCriterio;
    }

    public void agregarParametros(String clave, Object valor) {
        this.parametros.put(clave, valor);
    }

}


