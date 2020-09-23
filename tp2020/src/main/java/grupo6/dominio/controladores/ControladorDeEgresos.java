package grupo6.dominio.controladores;

import grupo6.dominio.entidades.OperacionDeEgreso;
import grupo6.dominio.repositorios.RepositorioEgresos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDeEgresos {

    private RepositorioEgresos repositorioEgresos;

    public ControladorDeEgresos(){
        this.repositorioEgresos = new RepositorioEgresos();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
//        Map<String, Object> parametros = new HashMap<>();
//        List<Usuario> usuarios = this.repo.buscarTodos();
//        parametros.put("usuarios", usuarios);
//        asignarUsuarioSiEstaLogueado(request, parametros);
        Map<String, Object> parametros = new HashMap<>();
        List<OperacionDeEgreso> egresos = repositorioEgresos.obtenerTodos();
        parametros.put("egresos", egresos);
        return new ModelAndView(parametros, "egresos/indice.hbs");
    }

    public ModelAndView crearEgreso(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros, "egresos/nuevo.hbs");
    }

    public Response guardarEgreso(Request request, Response response){
        OperacionDeEgreso egreso = new OperacionDeEgreso();
//        asignarAtributosA(usuario, request);
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
}
