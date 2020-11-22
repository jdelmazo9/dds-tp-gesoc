package grupo6.server;

//import domain.controllers.LoginController;
//import domain.controllers.UsuarioController;
//import domain.controllers.UsuarioRestControllerEjemplo;
//import domain.middleware.AuthMiddleware;

import grupo6.dominio.controladores.*;
import grupo6.dominio.entidades.AdaptadorVinculadorConcreto;

import grupo6.dominio.repositorios.RepositorioCriterios;

import grupo6.dominio.repositorios.RepositorioEgresos;
import grupo6.dominio.repositorios.RepositorioIngresos;

import grupo6.dominio.repositorios.RepositorioProveedores;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.ValidacionLongitud;
import grupo6.seguridad.ValidacionRegEx;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import grupo6.spark.utils.BooleanHelper;
import grupo6.spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
            .create()
            .withDefaultHelpers()
            .withHelper("isTrue", BooleanHelper.isTrue)
            .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        ControladorDeSeguridad controladorDeSeguridad = new ControladorDeSeguridad(new ValidacionLongitud(5), new ValidacionLongitud(5));
        ControladorDeUsuarios controladorDeUsuarios = new ControladorDeUsuarios(controladorDeSeguridad);
        ControladorDeSesion controladorDeSesion = new ControladorDeSesion(controladorDeUsuarios);
        ControladorDeCriterios controladorDeCriterios = new ControladorDeCriterios();
        ControladorDeEgresos controladorDeEgresos = new ControladorDeEgresos();


        ControladorDeVinculaciones controladorDeVinculaciones = new ControladorDeVinculaciones();

        ControladorDeIngresos controladorDeIngresos = new ControladorDeIngresos();
        RepositorioCriterios repositorioCriterios = RepositorioCriterios.getInstancia();
        repositorioCriterios.cargarCriteriosTest();

        ControladorDeValidaciones controladorDeValidaciones = ControladorDeValidaciones.getInstancia();
        RepositorioProveedores repositorioProveedores = RepositorioProveedores.getInstancia();
        repositorioProveedores.cargarProveedoresTest();

        RepositorioEgresos.getInstancia().cargarDatosTest();
        RepositorioIngresos.getInstancia().cargarDatosTest();

        try {
            controladorDeUsuarios.agregarUsuario("admin", "admin123", RolUsuario.ADMIN);
            controladorDeUsuarios.agregarUsuario("api_user", "api123456", RolUsuario.ESTANDAR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Welcome

        Spark.get("/", controladorDeSesion::inicio, Router.engine);
        Spark.get("/admin", controladorDeSesion::inicioAdmin, Router.engine);


        // Sesion

        Spark.get("/login", controladorDeSesion::nuevaSesion, Router.engine);
        Spark.post("/login", controladorDeSesion::logIn);
        Spark.get("/logout", controladorDeSesion::logOut);


        // Egresos

        Spark.get("/egresos", controladorDeEgresos::mostrarTodos, Router.engine);
        Spark.get("/egresos/nuevo", controladorDeEgresos::crearEgreso, Router.engine);
        Spark.post("/egresos", controladorDeEgresos::guardarEgreso);
        Spark.post("/egresos/:id", controladorDeEgresos::guardarEgreso);
        Spark.get("/egresos/:id", controladorDeEgresos::mostrarEgreso, Router.engine);
        Spark.delete("/egresos/:id", controladorDeEgresos::eliminar);
        Spark.post("/egresos/:id/items", controladorDeEgresos::agregarItem);
        Spark.delete("/egresos/:id/items/:id_item", controladorDeEgresos::eliminarItem);
        Spark.post("/egresos/:id/categorias", controladorDeEgresos::agregarCategorias);
        Spark.delete("/egresos/:id/categorias/:id_categoria", controladorDeEgresos::eliminarCategoria);
        Spark.post("/egresos/:id/cargar-json-presupuestos", controladorDeEgresos::cargarPresupuestos);
        Spark.delete("/egresos/:id/presupuestos/:id_presupuesto", controladorDeEgresos::eliminarPresupuesto);


        // Ingresos

        Spark.get("/ingresos", controladorDeIngresos::mostrarTodos, Router.engine);
        Spark.post("/ingresos/upload-json", controladorDeIngresos::cargarIngresos);


        // Vinculaciones

        Spark.get("/vinculacion", controladorDeVinculaciones::setUpVinculacion, Router.engine);
        Spark.post("/vinculacion", controladorDeVinculaciones::vincularEgresos);
        Spark.get("/vinculaciones", controladorDeVinculaciones::mostrarVinculaciones, Router.engine);



        // Criterios

        Spark.get("/criterios/:id", controladorDeCriterios::obtenerCriterio);


        // Api

//        Spark.before("/api/egresos",controladorDeSesion::verificarSesion);
        Spark.get("/api/egresos", controladorDeEgresos::obtenerTodos);
        Spark.post("/api/egresos/:id/validacion", controladorDeEgresos::nuevaValidacion);
        Spark.get("/api/egresos/:id/validacion", controladorDeEgresos::obtenerValidaciones);
        Spark.get("/api/egresos/validacion", controladorDeValidaciones::obtenerValidaciones);
        Spark.get("/api/ingresos", controladorDeIngresos::obtenerTodos);


        // Before Checks

        Spark.before("/vinculacion",controladorDeSesion::verificarSesion);
        Spark.before("/vinculaciones",controladorDeSesion::verificarSesion);
        Spark.before("/egresos",controladorDeSesion::verificarSesion);
        Spark.before("/egresos/*",controladorDeSesion::verificarSesion);
        Spark.before("/ingresos",controladorDeSesion::verificarSesion);
        Spark.before("/ingresos/*",controladorDeSesion::verificarSesion);
        Spark.before("/criterios",controladorDeSesion::verificarSesion);
        Spark.before("/criterios/*",controladorDeSesion::verificarSesion);
        Spark.before("/",controladorDeSesion::verificarSesion);
        Spark.before("/admin",controladorDeSesion::verificarSesionAdmin);

    }
}
