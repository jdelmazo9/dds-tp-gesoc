package grupo6.dominio.controladores;

import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.RepositorioEgresos;
import grupo6.dominio.repositorios.RepositorioIngresos;
import grupo6.dominio.repositorios.RepositorioProveedores;
import grupo6.spark.utils.FileUploadHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDeIngresos {

    private RepositorioIngresos repositorioIngresos;


    public ControladorDeIngresos(){
        this.repositorioIngresos = new RepositorioIngresos();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
//        Map<String, Object> parametros = new HashMap<>();
//        List<Usuario> usuarios = this.repo.buscarTodos();
//        parametros.put("usuarios", usuarios);
//        asignarUsuarioSiEstaLogueado(request, parametros);
        Map<String, Object> parametros = new HashMap<>();
        List<OperacionDeIngreso> ingresos = repositorioIngresos.obtenerTodos();
        parametros.put("ingresos", ingresos);
        return new ModelAndView(parametros, "ingresos.hbs");
    }
}
