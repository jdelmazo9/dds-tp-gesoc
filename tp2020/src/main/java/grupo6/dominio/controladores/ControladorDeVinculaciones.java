package grupo6.dominio.controladores;

import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.RepositorioEgresos;
import grupo6.dominio.repositorios.RepositorioIngresos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;


public class ControladorDeVinculaciones {

    AdaptadorVinculadorConcreto vinculador;
    ArrayList<Vinculacion> vinculaciones;
    String criterioUsado;

    public ControladorDeVinculaciones(){
        this.vinculador = new AdaptadorVinculadorConcreto();
        this.vinculaciones = new ArrayList<>();
        this.criterioUsado = "No hay vinculacion";
    }

    public CriteriosEnum convertirCriterio(String criterio){
        CriteriosEnum crit = null;
        switch (criterio) {
            case "CRITERIO_VALOR_PRIMERO_INGRESO":
                crit = CriteriosEnum.CRITERIO_VALOR_PRIMERO_INGRESO;
                this.criterioUsado = "Primero Ingreso";
                break;

            case "CRITERIO_VALOR_PRIMERO_EGRESO":
                crit =CriteriosEnum.CRITERIO_VALOR_PRIMERO_EGRESO;
                this.criterioUsado = "Primero Egreso";
                break;

            case "CRITERIO_FECHA":
                crit = CriteriosEnum.CRITERIO_FECHA;
                this.criterioUsado = "Por Fecha";
                break;

            case "CRITERIO_MIX_FECHA_VALOR":
                crit = CriteriosEnum.CRITERIO_MIX_FECHA_VALOR;
                this.criterioUsado = "Mix Fecha y Valor";
                break;

            case "CRITERIO_MIX_VALOR_FECHA":
                crit = CriteriosEnum.CRITERIO_MIX_VALOR_FECHA;
                this.criterioUsado = "Mix Valor y Fecha";
                break;
        }
        return crit;
    }

    public ModelAndView setUpVinculacion(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "egresos/vinculacion.hbs");
    }

    public Response vincularEgresos(Request request, Response response){
        ArrayList <OperacionDeEgreso> egresos = RepositorioEgresos.getInstancia().obtenerTodos();
        ArrayList <OperacionDeIngreso> ingresos = RepositorioIngresos.getInstancia().obtenerTodos();
        this.vinculaciones = this.vinculador.vincular(ingresos, egresos, convertirCriterio(request.queryParams("criterio")));
        System.out.println(vinculaciones);
        response.redirect("/vinculaciones");
        return response;
    }

    public ModelAndView mostrarVinculaciones(Request request, Response response) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("vinculaciones", vinculaciones);
                parametros.put("criterio", criterioUsado);
                return new ModelAndView(parametros, "egresos/vinculaciones.hbs");
            }
}
