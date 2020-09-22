package grupo6.dominio.entidades;

import java.util.List;
import java.util.stream.Collectors;

public class ValidadorLicitacionMenorPrecio extends ValidadorLicitacion {

    @Override
    public Boolean validarCriterio(OperacionDeEgreso operacion) {
        Boolean result = true;
        Double valorPagado = operacion.getValorTotal();
        List<Double> volores = operacion.getPresupuestos().stream().map(presu -> presu.getValorTotal())
                .collect(Collectors.toList());
        for(Double d : volores){
            if(d < valorPagado){
               result = false;
            }
         }
        return result;
    }

}
