package grupo6.dominio;

import java.util.ArrayList;

public abstract class ValidadorLicitacion {

    int cantidadMinimaDePresupuestos;

    public ValidadorLicitacion(){
        this.cantidadMinimaDePresupuestos = 0;
    }

    public void set_cantidadPresupuestos(int cantidad){
        this.cantidadMinimaDePresupuestos = cantidad;
    }

    public ResultadoValidacion validar(OperacionDeEgreso operacion){ //Este metodo después va a devolver un resultado (debería hacer un New)
        ResultadoValidacion result = new ResultadoValidacion();
        if (!(this.validarCantidad(operacion.getPresupuestos().size()))){
            result.set_resultado(false);
            result.set_mensaje_resultado("No se satisface la cantidad de presupuestos requeridos");
            return result;
        }
        else if (!(this.validarExistencia(operacion.getItems(), operacion.getPresupuestos()))){
            result.set_resultado(false);
            result.set_mensaje_resultado("La operacion no se realizo en base a un presupuesto");
            return result;
        }
        else if (!(validarCriterio(operacion))){
            result.set_resultado(false);
            result.set_mensaje_resultado("El presupuesto elegido no cumple con el Criterio solicitado");
            return result;
        }
        else{
            result.set_resultado(true);
            result.set_mensaje_resultado("Todo OK");
            return result;
        }
    }
    private Boolean validarCantidad(int cantPresupuestos){
        return cantPresupuestos >= this.cantidadMinimaDePresupuestos;
    }
    private Boolean validarExistencia(ArrayList<Item> items, ArrayList<Presupuesto> presupuestos){
        return presupuestos.stream().anyMatch(presu -> presu.getItems().equals(items));
    } // Valida que el egreso corresponda a un presupuesto existente
    public abstract Boolean validarCriterio(OperacionDeEgreso operacion);
    
}