package grupo6.dominio;

import java.util.HashMap;

public class CriterioAceptacion {
    private HashMap<String, Object> parametros;
    private TipoCriterio tipoCriterio;

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


