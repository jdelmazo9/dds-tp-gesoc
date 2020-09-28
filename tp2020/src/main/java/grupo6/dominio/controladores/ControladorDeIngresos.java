package grupo6.dominio.controladores;

import com.google.gson.Gson;
import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.Presupuesto;
import grupo6.dominio.repositorios.RepositorioCriterios;
import grupo6.dominio.repositorios.RepositorioEgresos;
import grupo6.dominio.repositorios.RepositorioIngresos;
import grupo6.dominio.repositorios.daos.OperacionDeIngresoAux;
import grupo6.spark.utils.FileUploadHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDeIngresos {

//    public String obtenerCriterio(Request request, Response response){
//        Gson gson = new Gson();
//        String nombre = RepositorioCriterios.getInstancia().getCriterio(request.params("id")).getNombre();
//        String json = gson.toJson(RepositorioCriterios.getInstancia().getCriterio(request.params("id")));
//        response.type("application/json");
//        return json;
//    }

    public Response cargarIngresos(Request request, Response response){
        OperacionDeIngresoAux temp = FileUploadHandler.readJsonTo(request, "fileToUpload", OperacionDeIngresoAux.class);
//        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(Integer.parseInt(request.params("id")));
//        egreso.setPresupuestos(egresoTmp.getPresupuestos());
        for(OperacionDeIngreso i: temp.getIngresos()){
            RepositorioIngresos.getInstancia().agregar(i);
//            System.out.println(i.getFecha());
//            System.out.println(i.getFechaStr());
        }
        response.redirect("/ingresos");
        return response;
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        List<OperacionDeIngreso> ingresos;

        ingresos = RepositorioIngresos.getInstancia().obtenerTodos();;

        parametros.put("ingresos", ingresos);
        return new ModelAndView(parametros, "ingresos/indice.hbs");
    }
}
