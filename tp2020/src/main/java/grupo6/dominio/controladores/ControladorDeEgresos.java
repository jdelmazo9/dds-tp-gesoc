package grupo6.dominio.controladores;

import com.google.gson.*;
import db.EntityManagerHelper;
import grupo6.bitacoraOperaciones.ServicioRegistroOperaciones;
import grupo6.bitacoraOperaciones.TipoOperacion;
import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.*;
import grupo6.dominio.repositorios.daos.OperacionDTO;
import grupo6.spark.utils.FileUploadHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ControladorDeEgresos {

//    private RepositorioEgresos repositorioEgresos;

//    public ControladorDeEgresos(){
//        this.repositorioEgresos = RepositorioEgresos.getInstancia();
//    }
//
//    public RepositorioEgresos getRepositorioEgresos() {
//        return repositorioEgresos;
//    }

    public ModelAndView mostrarTodos(Request request, Response response) {
//        Map<String, Object> parametros = new HashMap<>();
//        List<Usuario> usuarios = this.repo.buscarTodos();
//        parametros.put("usuarios", usuarios);
//        asignarUsuarioSiEstaLogueado(request, parametros);
        EntityManagerHelper.beginTransaction();

        Map<String, Object> parametros = new HashMap<>();
        List<OperacionDeEgreso> egresos;

        List<String> criterios = new ArrayList();
        List<String> categorias = new ArrayList();
        if(request.queryParamsValues("categoria") != null) {
            criterios = Arrays.asList(request.queryParamsValues("criterio"));
            categorias = Arrays.asList(request.queryParamsValues("categoria"));
        }

        if(criterios.isEmpty() || categorias.isEmpty()){
            egresos = RepositorioEgresos.getInstancia().obtenerTodos();
        }
        else{
            egresos = RepositorioEgresos.getInstancia().obtenerTodos(criterios, categorias);
        }
        parametros.put("egresos", egresos);
        parametros.put("criterios", RepositorioCriterios.getInstancia().obtenerTodos());
        parametros.put("repoCriterios", RepositorioCriterios.getInstancia());
        EntityManagerHelper.commit();

        return new ModelAndView(parametros, "egresos/indice.hbs");
    }

    public String obtenerTodos(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        List<String> criterios = new ArrayList();
        List<String> categorias = new ArrayList();
        if(request.queryParamsValues("categoria") != null) {
            criterios = Arrays.asList(request.queryParamsValues("criterio"));
            categorias = Arrays.asList(request.queryParamsValues("categoria"));
        }
        List<OperacionDeEgreso> egresos;
        if(criterios.isEmpty() || categorias.isEmpty()){
            egresos = RepositorioEgresos.getInstancia().obtenerTodos();
        }
        else{
            egresos = RepositorioEgresos.getInstancia().obtenerTodos(criterios, categorias);
        }
        for (OperacionDeEgreso e: egresos ) {
            System.out.println(e.getId());
        }
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies( new HiddenAnnotationExclusionStrategy() );
        Gson gson = builder.setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
        String json = gson.toJson(egresos);
        System.out.println(json);
        response.type("application/json");
        EntityManagerHelper.commit();

        return json;
    }

    public ModelAndView crearEgreso(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("proveedores",RepositorioProveedores.getInstancia().obtenerTodos());
        parametros.put("repoCriterios", RepositorioCriterios.getInstancia());
        parametros.put("mediosDePago", RepositorioMediosDePago.getInstancia().getMedioDePagos());
        EntityManagerHelper.commit();

        return new ModelAndView(parametros, "egresos/nuevo.hbs");
    }

    public Response guardarEgreso(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDeEgreso egreso;
        TipoOperacion tipo;
        if(request.params("id") == null){
            egreso = new OperacionDeEgreso();
            tipo = TipoOperacion.CREATE;
        }
        else{
            egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
            tipo = TipoOperacion.UPDATE;
        }
//        asignarAtributosA(usuario, request);
        if(request.queryParams("proveedor") != null){
            Proveedor proveedor = RepositorioProveedores.getInstancia().buscar(new Integer(request.queryParams("proveedor")));
            egreso.setProveedor(proveedor);
        }
        System.out.println(request.queryParams("medioDePago"));
        if(request.queryParams("medioDePago") != null){
            MedioDePago medioDePago = RepositorioMediosDePago.getInstancia().buscar(request.queryParams("medioDePago"));
            egreso.setMedioDePago(medioDePago);
        }
        if(!request.queryParams("fecha").equals("")){
            egreso.setFecha(LocalDate.parse(request.queryParams("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        if(request.params("id") == null){
            RepositorioEgresos.getInstancia().agregar(egreso);
        } else {
            RepositorioEgresos.getInstancia().modificar(egreso);
        }
        response.redirect("/egresos/" + egreso.getId());

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            tipo,
            egreso.getId()
        );
        EntityManagerHelper.commit();
        return response;

    }

    public Response eliminar(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
        RepositorioEgresos.getInstancia().eliminar(egreso);

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.DELETE,
            egreso.getId()
        );
        EntityManagerHelper.commit();
        return response;
    }

    public Response eliminarItem(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
//        System.out.println(egreso.getItems().size());
        Item item = egreso.buscarItem(new Integer(request.params("id_item")));
//        System.out.println(item.getDescripcion());
        egreso.eliminarItem(item);
        RepositorioEgresos.getInstancia().modificar(egreso);

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.UPDATE,
            egreso.getId()
        );
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "items",
            TipoOperacion.DELETE,
            item.getId()
        );
//        System.out.println(egreso.getItems().size());
        EntityManagerHelper.commit();
        return response;
    }


    public ModelAndView mostrarEgreso(Request request, Response response) {
        EntityManagerHelper.beginTransaction();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("egreso", RepositorioEgresos.getInstancia().buscar(Integer.parseInt(request.params("id"))));
        parametros.put("proveedores",RepositorioProveedores.getInstancia().obtenerTodos());
        parametros.put("repoCriterios", RepositorioCriterios.getInstancia());
        parametros.put("mediosDePago", RepositorioMediosDePago.getInstancia().getMedioDePagos());
        EntityManagerHelper.commit();
        return new ModelAndView(parametros, "egresos/mostrar.hbs");
    }

    public Response cargarPresupuestos(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDTO egresoTmp = FileUploadHandler.readJsonTo(request, "fileToUpload", OperacionDTO.class);
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(Integer.parseInt(request.params("id")));

        for (Presupuesto p: egresoTmp.getPresupuestos()) {
            Proveedor proveedor = RepositorioProveedores.getInstancia().buscarProveedor(p.getProveedor().getNroIdentificacion());
            if(proveedor == null) {
                System.out.println("El proveedor no existe");
            }
            p.setProveedor(proveedor);
        }

        egreso.addPresupuestos(
            egresoTmp.getPresupuestos().stream().
                filter(presupuesto -> presupuesto.getProveedor() != null).collect(Collectors.toList()));
//        egreso.addPresupuestos(egresoTmp.getPresupuestos());
        RepositorioEgresos.getInstancia().modificar(egreso);

//        for (Presupuesto p: egreso.getPresupuestos()) {
//            System.out.println(p.getValorTotal());
//            System.out.println(p.getProveedor().getNombre());
//            System.out.println(p.getId());
//        }

        response.redirect("/egresos/"+request.params("id"));

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.UPDATE,
            egreso.getId()
        );
        for (Presupuesto p: egreso.getPresupuestos()) {
            ServicioRegistroOperaciones.getInstancia().registrarOperacion(
                sesion.getUsuario().getNombre(),
                "presupuestos",
                TipoOperacion.CREATE,
                p.getId()
            );
        }
        EntityManagerHelper.commit();

        return response;
    }

    public Response eliminarPresupuesto(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
        Presupuesto presu = egreso.getPresupuesto(new Integer(request.params("id_presupuesto")));
        egreso.getPresupuestos().remove(presu);
        RepositorioEgresos.getInstancia().modificar(egreso);

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.UPDATE,
            egreso.getId()
        );
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "presupuestos",
            TipoOperacion.DELETE,
            presu.getId()
        );
        EntityManagerHelper.commit();

        return response;
    }

    public Response agregarItem(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        Item unItem = new Item(TipoItem.valueOf(request.queryParams("Tipo")), request.queryParams("Descripcion"), Double.parseDouble(request.queryParams("Valor")));
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
        egreso.agregarItem(unItem);
        RepositorioEgresos.getInstancia().modificar(egreso);
        response.redirect("/egresos/"+request.params("id"));

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.UPDATE,
            egreso.getId()
        );
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "items",
            TipoOperacion.CREATE,
            unItem.getId()
        );
        EntityManagerHelper.commit();
        return response;
    }

    public Response agregarCategorias(Request request, Response response) {
        EntityManagerHelper.beginTransaction();
        Categoria unaCategoria = RepositorioCriterios.getInstancia().buscar(request.queryParams("criterio")).buscar(request.queryParams("categoria"));
        //        Categoria unaCategoria = new Categoria(request.queryParams("Nombre"), request.queryParams("Criterio"));
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
        egreso.agregarCategoria(unaCategoria);
        RepositorioEgresos.getInstancia().modificar(egreso);
        response.redirect("/egresos/" + request.params("id"));
//        System.out.println(egreso.getCategorias());

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.UPDATE,
            egreso.getId()
        );
        EntityManagerHelper.commit();
        return response;
    }

    public Response eliminarCategoria(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(new Integer(request.params("id")));
        Categoria cat = egreso.getCategoria(new Integer(request.params("id_categoria")));
        egreso.getCategorias().remove(cat);
        RepositorioEgresos.getInstancia().modificar(egreso);

        // Guardo la modificacion hecha en la bitacora de operaciones
        Sesion sesion = request.session().attribute("sesion");
        ServicioRegistroOperaciones.getInstancia().registrarOperacion(
            sesion.getUsuario().getNombre(),
            "egresos",
            TipoOperacion.UPDATE,
            egreso.getId()
        );
        EntityManagerHelper.commit();
        return response;
    }

    public String nuevaValidacion(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        OperacionDeEgreso egreso = RepositorioEgresos.getInstancia().buscar(Integer.parseInt(request.params("id")));
        egreso.suscribirComoRevisor(ControladorDeValidaciones.getInstancia().getBandejaDeMensajes());
        egreso.validarLicitacion();
        response.status(201);
        response.type("text/xml");
        response.body("Validacion creada. Consulte las validaciones del egreso para obtener el resultado");
        EntityManagerHelper.commit();
        return response.body();
    }

    public String obtenerValidaciones(Request request, Response response){
        EntityManagerHelper.beginTransaction();
        String json = ControladorDeValidaciones.getInstancia().obtenerValidacionesEgreso(Integer.parseInt(request.params("id")));
        response.type("application/json");
        EntityManagerHelper.commit();
        return json;
    }
}
