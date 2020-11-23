package grupo6.dominio.controladores;

import grupo6.dominio.entidades.Sesion;
import grupo6.dominio.repositorios.RepositorioDeUsuarios;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

public class ControladorDeSesion {
    Map<Integer, Sesion> sesiones ;
//    private Usuario usuarioActivo;
//    private LocalTime horaInicioSesion = null;
//    private LocalTime horaFinSesion = null;
//    private boolean hayUsuarioLogueado = false;
    private ControladorDeUsuarios controladorDeUsuarios; //@todo tiene un controlador de usuarios o lo hacemos standalone?

    public ControladorDeSesion(ControladorDeUsuarios cont){
        this.controladorDeUsuarios = cont;
        sesiones = new HashMap<>();
    }

    public ModelAndView inicio(Request request, Response response){
//        System.out.println((char[]) request.session().attribute("id"));

        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"welcome.hbs");
    }

    public ModelAndView inicioAdmin(Request request, Response response){
        //        System.out.println((char[]) request.session().attribute("id"));

                Map<String, Object> parametros = new HashMap<>();
                return new ModelAndView(parametros,"admin.hbs");
            }

    public Response verificarSesion(Request request, Response response){
        if(request.session(false) == null /*|| request.session(false).isNew()*/){
            response.redirect("/login");
            return response;
        }

        Sesion sesion = request.session().attribute("sesion");
        if(sesion == null){
            request.session(false).invalidate();
            response.redirect("/login");
            return response;
        }
//        else if (usuarioActivo.getRol() == RolUsuario.ADMIN){
//        else if (sesiones.get(request.session().attribute("id")).getUsuario().getRol() == RolUsuario.ADMIN){
        if(sesion.getUsuario().getRol() == RolUsuario.ADMIN){
            System.out.println("Su Rol de usuario no tiene acceso a esta ruta.");
            response.redirect("/admin");
        }
        return response;
    }

    public Response verificarSesionAdmin(Request request, Response response){
        if(request.session(false) == null){
            response.redirect("/login");
            return response;
        }
        Sesion sesion = request.session().attribute("sesion");
        if(sesion == null){
            request.session(false).invalidate();
            response.redirect("/login");
            return response;
        }
        if (sesion.getUsuario().getRol() != RolUsuario.ADMIN){
            System.out.println("Su Rol de usuario no tiene acceso a esta ruta.");
            response.redirect("/");
        }
        return response;
    }

    public ModelAndView nuevaSesion(Request request, Response response){
        if( request.session(false) != null ){
//            throw new Exception("Ya hay un usuario logueado");
            response.body("Ya estas logueado salame");
            response.redirect("/");
        }
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }

    public Response logIn(Request request, Response response) throws Exception{

        String nombre          = request.queryParams("nombreDeUsuario");
        String contrasenia     = request.queryParams("contrasenia");


//        System.out.println(request.session(false).isNew());


        if( request.session(false) != null /*&& !request.session(false).isNew() */){
//            throw new Exception("Ya hay un usuario logueado");
            response.body("Ya estas logueado salame");
            response.redirect("/");
        }
        else if( !controladorDeUsuarios.validarUsuarioContrasenia(nombre, contrasenia) ){
            System.out.println("El usuario y/o la contraseña ingresados no son correctos");
            response.redirect("/login");
        }
        else{
            System.out.println("lo estoy haciendo");

            request.session(true);
            Usuario usuario = RepositorioDeUsuarios.getInstancia().buscarUsuario(nombre, contrasenia);

            request.session().attribute("sesion", new Sesion(usuario));
//            request.session().attribute("id", usuarioActivo.getId());
//            System.out.println((String)request.session().attribute("id"));

//            horaInicioSesion = LocalTime.now();
            response.redirect("/");
//            hayUsuarioLogueado = true;
        }

        return response;
    }

//    public void logIn(String nombre, String contrasenia) throws Exception {
//        if( !request.session().isNew() ){
//            throw new Exception("Ya hay un usuario logueado");
//        }
//        else if( !controladorDeUsuarios.validarUsuarioContrasenia(nombre, contrasenia) ){
//            System.out.println("El usuario y/o la contraseña ingresados no son correctos");
//        }
//        else{
//            usuarioActivo = controladorDeUsuarios.getUsuario(nombre);
//            horaInicioSesion = LocalTime.now();
//            hayUsuarioLogueado = true;
//        }
//    }

    public Response logOut(Request request, Response response){
//        System.out.println((String) request.session(false).attribute("id"));

        if(request.session(false) != null) {
            Sesion sesion = request.session().attribute("sesion");
            sesion.setHoraFinSesion(LocalTime.now());
//            horaFinSesion = LocalTime.now();
//            hayUsuarioLogueado = false;
            request.session(false).invalidate();
        }
        response.redirect("/login");
        return response;
    }

//    public void logOut(){
//        if(hayUsuarioLogueado) {
//            horaFinSesion = LocalTime.now();
//            hayUsuarioLogueado = false;
//        }
//    }
//
//    public boolean hayUsuarioLogueado(){
//        return this.hayUsuarioLogueado;
//    }

//    public Usuario getUsuarioActivo(){
//        return usuarioActivo;
//    }
//
//    public LocalTime getHoraInicioDeSesion(){
//        return horaInicioSesion;
//    }
//
//    public LocalTime getHoraFinDeSesion(){
//        return horaFinSesion;
//    }
}
