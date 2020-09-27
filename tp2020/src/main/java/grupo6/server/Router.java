package grupo6.server;


//import domain.controllers.LoginController;
//import domain.controllers.UsuarioController;
//import domain.controllers.UsuarioRestControllerEjemplo;
//import domain.middleware.AuthMiddleware;
import grupo6.dominio.controladores.*;
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
        ControladorDeSeguridad controladorDeSeguridad = new ControladorDeSeguridad(new ValidacionRegEx(), new ValidacionLongitud(5));
        ControladorDeUsuarios controladorDeUsuarios = new ControladorDeUsuarios(controladorDeSeguridad);
        ControladorDeSesion controladorDeSesion = new ControladorDeSesion(controladorDeUsuarios);
        ControladorDeEgresos controladorDeEgresos = new ControladorDeEgresos();
        ControladorDeValidaciones controladorDeValidaciones = ControladorDeValidaciones.getInstancia();
        RepositorioProveedores repositorioProveedores = RepositorioProveedores.getInstancia();
        repositorioProveedores.cargarProveedoresTest();

        try {
            controladorDeUsuarios.agregarUsuario("admin", "admin123", RolUsuario.ADMIN);
            controladorDeUsuarios.agregarUsuario("api_user", "api123456", RolUsuario.ESTANDAR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spark.get("/", controladorDeSesion::inicio, Router.engine);
//        Spark.get("/", (request, response) -> {return "Hi Mundo";});

        // Sesion
        Spark.get("/login", controladorDeSesion::nuevaSesion, Router.engine);
        Spark.post("/login", controladorDeSesion::logIn);
        Spark.get("/logout", controladorDeSesion::logOut);
//        Spark.before( "/egresos/*", controladorDeSesion::verificarSesion); //@todo: restingir el acceso a users logueados

        // Egresos
        Spark.get("/egresos", controladorDeEgresos::mostrarTodos, Router.engine);
        Spark.get("/egresos/nuevo", controladorDeEgresos::crearEgreso, Router.engine);
        Spark.post("/egresos", controladorDeEgresos::guardarEgreso);
        Spark.get("/egresos/:id", controladorDeEgresos::mostrarEgreso, Router.engine);
        Spark.delete("/egresos/:id", controladorDeEgresos::eliminar);

//        Spark.get("/egresos/cargar-json-presupuestos", controladorDeEgresos::cargarPresupuestos, Router.engine);
        Spark.post("/egresos/:id/cargar-json-presupuestos", controladorDeEgresos::cargarPresupuestos);

        // Categorias


        // Api
//        Spark.before("/api/*", controlador);
        Spark.post("/api/egresos/:id/validacion", controladorDeEgresos::nuevaValidacion);
        Spark.get("/api/egresos/:id/validacion", controladorDeEgresos::obtenerValidaciones);
        Spark.get("/api/egresos/validacion", controladorDeValidaciones::obtenerValidaciones);

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
