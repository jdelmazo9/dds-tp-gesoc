package grupo6.dominio.entidades;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorLicitacionMenorPrecio extends ValidadorLicitacion {

    @Override
    public Boolean validarCriterio(OperacionDeEgreso operacion) {
        Double valorPagado = operacion.getValorTotal();
        List<Double> valores = operacion.getPresupuestos().stream().map(presu -> presu.getValorTotal())
                .collect(Collectors.toList());
        Proveedor proveedorElegido = operacion.getProveedor();
        if(proveedorElegido == null)
            return false;
        Presupuesto presupuestoElegido = operacion.getPresupuestos()
            .stream()
            .filter(p -> p.getProveedor().getNroIdentificacion() == proveedorElegido.getNroIdentificacion() )
            .findFirst().orElse(null);
        if( presupuestoElegido == null) {
            return false; // El proveedor elegido no presento presupuestos
        }
        // Elegi el mas barato (o uno de los mas baratos) y el valor que pague coincide
        return presupuestoElegido.getValorTotal() == valores.stream().min(Comparator.naturalOrder()).get()
            && presupuestoElegido.getValorTotal() == valorPagado;
//        for(Double d : valores){
//            if(d < valorPagado){
//               result = false;
//            }
//         }
    }

}
