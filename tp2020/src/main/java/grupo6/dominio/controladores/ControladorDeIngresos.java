package grupo6.dominio.controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grupo6.bitacoraOperaciones.ServicioRegistroOperaciones;
import grupo6.bitacoraOperaciones.TipoOperacion;
import grupo6.dominio.entidades.HiddenAnnotationExclusionStrategy;
import grupo6.dominio.entidades.OperacionDeIngreso;
import grupo6.dominio.entidades.Sesion;
import grupo6.dominio.repositorios.RepositorioCriterios;
import grupo6.dominio.repositorios.RepositorioIngresos;
import grupo6.dominio.repositorios.daos.OperacionDTO;
import grupo6.spark.utils.FileUploadHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.text.DateFormat;
import java.util.*;

public class ControladorDeIngresos {

//    public String obtenerCriterio(Request request, Response response){
//        Gson gson = new Gson();
//        String nombre = RepositorioCriterios.getInstancia().getCriterio(request.params("id")).getNombre();
//        String json = gson.toJson(RepositorioCriterios.getInstancia().getCriterio(request.params("id")));
//        response.type("application/json");
//        return json;
//    }

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

            // Guardo la modificacion hecha en la bitacora de operaciones
            Sesion sesion = request.session().attribute("sesion");
            ServicioRegistroOperaciones.getInstancia().registrarOperacion(
                sesion.getUsuario().getNombre(),
                "ingresos",
                TipoOperacion.CREATE,
                i.getId()
            );
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
        for (OperacionDeIngreso i: ingresos) {
            System.out.println(i.getDescripcion());
        }
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies( new HiddenAnnotationExclusionStrategy() );
        Gson gson = builder.create();
        String json = gson.toJson(ingresos);
        response.type("application/json");
        System.out.println(json);
        return json;
    }
}
