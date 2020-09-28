package grupo6.dominio.controladores;

import com.google.gson.Gson;
import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.RepositorioCriterios;
import grupo6.dominio.repositorios.RepositorioEgresos;
import grupo6.dominio.repositorios.RepositorioProveedores;
import grupo6.spark.utils.FileUploadHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControladorDeEgresos {

    private RepositorioEgresos repositorioEgresos;

    public ControladorDeEgresos(){
        this.repositorioEgresos = RepositorioEgresos.getInstancia();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
//        Map<String, Object> parametros = new HashMap<>();
//        List<Usuario> usuarios = this.repo.buscarTodos();
//        parametros.put("usuarios", usuarios);
//        asignarUsuarioSiEstaLogueado(request, parametros);

        Map<String, Object> parametros = new HashMap<>();
        List<OperacionDeEgreso> egresos;

        List<String> criterios = new ArrayList();
        List<String> categorias = new ArrayList();
        if(request.queryParamsValues("categoria") != null) {
            criterios = Arrays.asList(request.queryParamsValues("criterio"));
            categorias = Arrays.asList(request.queryParamsValues("categoria"));
        }

        if(criterios.isEmpty() || categorias.isEmpty()){
            egresos = repositorioEgresos.obtenerTodos();
        }
        else{
            egresos = repositorioEgresos.obtenerTodos(criterios, categorias);
        }
        parametros.put("egresos", egresos);
        parametros.put("criterios", RepositorioCriterios.getInstancia().obtenerTodos());
        parametros.put("repoCriterios", RepositorioCriterios.getInstancia());
        return new ModelAndView(parametros, "egresos/indice.hbs");
    }

    public String obtenerTodos(Request request, Response response){
        List<String> criterios = new ArrayList();
        List<String> categorias = new ArrayList();
        if(request.queryParamsValues("categoria") != null) {
            criterios = Arrays.asList(request.queryParamsValues("criterio"));
            categorias = Arrays.asList(request.queryParamsValues("categoria"));
        }
        List<OperacionDeEgreso> egresos;
        if(criterios.isEmpty() || categorias.isEmpty()){
            egresos = repositorioEgresos.obtenerTodos();
        }
        else{
            egresos = repositorioEgresos.obtenerTodos(criterios, categorias);
        }
        String json = new Gson().toJson(egresos);
        response.type("application/json");
        return json;
    }

    public ModelAndView crearEgreso(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("proveedores",RepositorioProveedores.getInstancia().obtenerTodos());
        return new ModelAndView(parametros, "egresos/nuevo.hbs");
    }

    public Response guardarEgreso(Request request, Response response){
        OperacionDeEgreso egreso = new OperacionDeEgreso();
//        asignarAtributosA(usuario, request);
        if(request.queryParams("proveedor") != null){
            Proveedor proveedor = RepositorioProveedores.getInstancia().buscar(new Integer(request.queryParams("proveedor")));
            egreso.setProveedor(proveedor);
        }
        if(!request.queryParams("fecha").equals("")){
            egreso.setFecha(LocalDate.parse(request.queryParams("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }


        // Hardcodeamos para probar filtros
        Criterio crit = new Criterio();
        crit.setNombre("TipoProveedor");
        Categoria cat = new Categoria("Nacional");
        cat.vincularCriterio(crit);
        egreso.agregarCategoria(cat);

        Criterio crit2 = new Criterio();
        crit2.setNombre("Provincia");
        Categoria cat2 = new Categoria("Buenos Aires");
        cat2.vincularCriterio(crit2);
        egreso.agregarCategoria(cat2);
        //

        this.repositorioEgresos.agregar(egreso);
        response.redirect("/egresos");
        return response;
    }

    public Response eliminar(Request request, Response response){
        OperacionDeEgreso egreso = this.repositorioEgresos.buscar(new Integer(request.params("id")));
        this.repositorioEgresos.eliminar(egreso);
        return response;
    }

    public ModelAndView mostrarEgreso(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("egreso", this.repositorioEgresos.buscar(Integer.parseInt(request.params("id"))));
        return new ModelAndView(parametros, "egresos/mostrar.hbs");
    }

    public Response cargarPresupuestos(Request request, Response response){
        OperacionDeEgreso egresoTmp = FileUploadHandler.readJsonTo(request, "fileToUpload", OperacionDeEgreso.class);
        OperacionDeEgreso egreso = this.repositorioEgresos.buscar(Integer.parseInt(request.params("id")));
        egreso.setPresupuestos(egresoTmp.getPresupuestos());

        for (Presupuesto p: egreso.getPresupuestos()) {
            System.out.println(p.getValorTotal());
            System.out.println(p.getProveedor().getNombre());
        }

        response.redirect("/egresos/"+request.params("id"));
        return response;
    }
}
