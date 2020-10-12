package grupo6.dominio.controladores;

import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.repositorios.RepositorioCriterios;
import grupo6.dominio.repositorios.RepositorioIngresos;
import grupo6.dominio.repositorios.daos.OperacionDTO;
import grupo6.spark.utils.FileUploadHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ControladorDeIngresos {

//    public String obtenerCriterio(Request request, Response response){
//        Gson gson = new Gson();
//        String nombre = RepositorioCriterios.getInstancia().getCriterio(request.params("id")).getNombre();
//        String json = gson.toJson(RepositorioCriterios.getInstancia().getCriterio(request.params("id")));
//        response.type("application/json");
//        return json;
//    }

    public Response cargarIngresos(Request request, Response response){
        OperacionDTO temp = FileUploadHandler.readJsonTo(request, "fileToUpload", OperacionDTO.class);
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

        List<String> criterios = new ArrayList();
        List<String> categorias = new ArrayList();
        ingresos = RepositorioIngresos.getInstancia().obtenerTodos();

        if(request.queryParamsValues("categoria") != null) {
            criterios = Arrays.asList(request.queryParamsValues("criterio"));
            categorias = Arrays.asList(request.queryParamsValues("categoria"));
        }

        if(criterios.isEmpty() || categorias.isEmpty()){
            ingresos = RepositorioIngresos.getInstancia().obtenerTodos();
        }
        else{
            ingresos = RepositorioIngresos.getInstancia().obtenerTodos(criterios, categorias);
        }
        parametros.put("ingresos", ingresos);
        parametros.put("criterios", RepositorioCriterios.getInstancia().obtenerTodos());
        parametros.put("repoCriterios", RepositorioCriterios.getInstancia());

        ;
        
        return new ModelAndView(parametros, "ingresos/indice.hbs");
    }

    public String obtenerTodos(Request request, Response response){
        List<String> criterios = new ArrayList();
        List<String> categorias = new ArrayList();
        if(request.queryParamsValues("categoria") != null) {
            criterios = Arrays.asList(request.queryParamsValues("criterio"));
            categorias = Arrays.asList(request.queryParamsValues("categoria"));
        }
        List<OperacionDeIngreso> ingresos;
        if(criterios.isEmpty() || categorias.isEmpty()){
            ingresos = RepositorioIngresos.getInstancia().obtenerTodos();
        }
        else{
            ingresos = RepositorioIngresos.getInstancia().obtenerTodos(criterios, categorias);
        }
        String json = new Gson().toJson(ingresos);
        response.type("application/json");
        return json;
    }
}
