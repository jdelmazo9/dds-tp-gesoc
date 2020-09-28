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
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
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
        ControladorDeSeguridad controladorDeSeguridad = new ControladorDeSeguridad(new ValidacionRegEx(), new ValidacionLongitud(5));
        ControladorDeUsuarios controladorDeUsuarios = new ControladorDeUsuarios(controladorDeSeguridad);
        ControladorDeSesion controladorDeSesion = new ControladorDeSesion(controladorDeUsuarios);
        ControladorDeCriterios controladorDeCriterios = new ControladorDeCriterios();
        ControladorDeEgresos controladorDeEgresos = new ControladorDeEgresos();

        ControladorDeVinculaciones controladorDeVinculaciones = new ControladorDeVinculaciones();

        ControladorDeIngresos controladorDeIngresos = new ControladorDeIngresos();
        RepositorioCriterios repositorioCriterios = RepositorioCriterios.getInstancia();
        repositorioCriterios.cargarCriteriosTest();

        RepositorioProveedores repositorioProveedores = RepositorioProveedores.getInstancia();
        repositorioProveedores.cargarProveedoresTest();

        RepositorioEgresos.getInstancia().cargarDatosTest();
        RepositorioIngresos.getInstancia().cargarDatosTest();

        try {
            controladorDeUsuarios.agregarUsuario("admin", "admin123", RolUsuario.ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spark.get("/", controladorDeSesion::inicio, Router.engine);
//        Spark.get("/", (request, response) -> {return "Hi Mundo";});

        // Sesion
        Spark.get("/login", controladorDeSesion::nuevaSesion, Router.engine);
        Spark.post("/login", controladorDeSesion::logIn);
        Spark.get("/logout", controladorDeSesion::logOut);

        //#region Before Checks
        Spark.before("/egresos",controladorDeSesion::verificarSesion);
        Spark.before("/egresos/*",controladorDeSesion::verificarSesion);
        Spark.before("/",controladorDeSesion::verificarSesion);
        Spark.before("/vinculacion",controladorDeSesion::verificarSesion);
        Spark.before("/vinculaciones",controladorDeSesion::verificarSesion);

        //#endregion

        // Egresos
        Spark.get("/egresos", controladorDeEgresos::mostrarTodos, Router.engine);
        Spark.get("/api/egresos", controladorDeEgresos::obtenerTodos);
        Spark.get("/egresos/nuevo", controladorDeEgresos::crearEgreso, Router.engine);
        Spark.post("/egresos", controladorDeEgresos::guardarEgreso);
        Spark.post("/egresos/:id", controladorDeEgresos::guardarEgreso);
        Spark.get("/egresos/:id", controladorDeEgresos::mostrarEgreso, Router.engine);
        Spark.delete("/egresos/:id", controladorDeEgresos::eliminar);
        Spark.post("/egresos/:id/items", controladorDeEgresos::agregarItem);
        Spark.post("/egresos/:id/categorias", controladorDeEgresos::agregarCategorias);


//        Spark.get("/egresos/cargar-json-presupuestos", controladorDeEgresos::cargarPresupuestos, Router.engine);
        Spark.post("/egresos/:id/cargar-json-presupuestos", controladorDeEgresos::cargarPresupuestos);


        // Categorias

        //#region Vinculación

        Spark.get("/vinculacion", controladorDeVinculaciones::setUpVinculacion, Router.engine);
        Spark.post("/vinculacion", controladorDeVinculaciones::vincularEgresos);
        Spark.get("/vinculaciones", controladorDeVinculaciones::mostrarVinculaciones, Router.engine);


        // Criterios
        Spark.get("/criterios/:id", controladorDeCriterios::obtenerCriterio);

        Spark.get("/ingresos", controladorDeIngresos::mostrarTodos, Router.engine);
        Spark.post("/ingresos/upload-json", controladorDeIngresos::cargarIngresos);

        //el Before
        Spark.before("/egresos",controladorDeSesion::verificarSesion);
        Spark.before("/egresos/*",controladorDeSesion::verificarSesion);
        Spark.before("/api/egresos",controladorDeSesion::verificarSesion);
        Spark.before("/ingresos",controladorDeSesion::verificarSesion);
        Spark.before("/ingresos/*",controladorDeSesion::verificarSesion);
        Spark.before("/criterios",controladorDeSesion::verificarSesion);
        Spark.before("/criterios/*",controladorDeSesion::verificarSesion);
        Spark.before("/",controladorDeSesion::verificarSesion);
        //Spark.before("/vinculacion",controladorDeSesion::verificarSesion);
        //Spark.before("/vinculaciones",controladorDeSesion::verificarSesion);



//        UsuarioRestControllerEjemplo usuarioRestControllerEjemplo = new UsuarioRestControllerEjemplo();
//        UsuarioController usuarioController = new UsuarioController();
//        LoginController loginController     = new LoginController();
//        AuthMiddleware authMiddleware       = new AuthMiddleware();

//        Spark.get("/", loginController::inicio, Router.engine);
//
//        Spark.before("/", authMiddleware::verificarSesion);
//
//        Spark.post("/login", loginController::login);
//
//        Spark.get("/logout", loginController::logout);
//
//        Spark.get("/usuarios", usuarioController::mostrarTodos, Router.engine);
//
//        Spark.get("/usuario/:id", usuarioController::mostrar, Router.engine);
//
//        Spark.get("/usuario", usuarioController::crear, Router.engine);
//
//        Spark.post("/usuario/:id", usuarioController::modificar);
//
//        Spark.post("/usuario", usuarioController::guardar);
//
//        Spark.delete("/usuario/:id", usuarioController::eliminar);
//
//        Spark.get("/api/usuario/:id", usuarioRestControllerEjemplo::mostrar);
    }
}
