package grupo6.dominio.cargaDataInicial;

import db.EntityManagerHelper;
import grupo6.dominio.entidades.*;
import grupo6.dominio.repositorios.*;
import grupo6.dominio.servicios.AdapterMediosDePagoMP;
import grupo6.seguridad.RolUsuario;
import grupo6.seguridad.Usuario;

import java.time.LocalDate;

public class CargaInicialBD {

    public static void cargaInicial() {
        EntityManagerHelper.beginTransaction();
        cargaUsuarios();
        cargaCriterios();
        cargaEgresos();
        cargaIngresos();
        cargaProveedores();
        cargaMediosDePago();
        EntityManagerHelper.commit();
    }

    private static void cargaMediosDePago(){
        RepositorioMediosDePago repositorioMediosDePago = RepositorioMediosDePago.getInstancia();
        for(MedioDePago m: new AdapterMediosDePagoMP().getMediosDePago()) {
            repositorioMediosDePago.agregar(m);
        }
    }

    private static void cargaCriterios(){
        RepositorioCriterios repoCriterios = RepositorioCriterios.getInstancia();

        //criterio TipoProveedor / Categorias: Nacional, Internacional
        Criterio criterioTipoProveedor = new Criterio();
        criterioTipoProveedor.setNombre("TipoProveedor");
        new Categoria("Nacional", criterioTipoProveedor);
        new Categoria("Internacional", criterioTipoProveedor);
        repoCriterios.agregar(criterioTipoProveedor);

        //criterio Provincia / Categorias: Buenos Aires, Entre Ríos, Córdoba
        Criterio criterioProvincia = new Criterio();
        criterioProvincia.setNombre("Provincia");
        new Categoria("Buenos Aires", criterioProvincia);
        new Categoria("Entre Ríos", criterioProvincia);
        new Categoria("Córdoba", criterioProvincia);
        repoCriterios.agregar(criterioProvincia);

        Criterio criterioTipoIngreso = new Criterio();
        criterioTipoIngreso.setNombre("TipoIngreso");
        new Categoria("donacion", criterioTipoIngreso);
        repoCriterios.agregar(criterioTipoIngreso);
    }

    private static void cargaEgresos() {
        OperacionDeEgreso e1, e2, e3;
        e1 = new OperacionDeEgreso(LocalDate.parse("2018-03-28"));
        e2 = new OperacionDeEgreso(LocalDate.parse("2018-03-30"));
        e3 = new OperacionDeEgreso(LocalDate.parse("2018-03-29"));

        e1.agregarItem(new Item(TipoItem.Articulo, "Microondas marca Samsung", 1200.0));
        e2.agregarItem(new Item(TipoItem.Articulo, "Heladera marca Hp", 1700.00));
        e3.agregarItem(new Item(TipoItem.Articulo, "Lavarropas marca Hp", 1800.00));

        RepositorioEgresos repoEgresos = RepositorioEgresos.getInstancia();
        repoEgresos.agregar(e1);
        repoEgresos.agregar(e2);
        repoEgresos.agregar(e3);
    }

    private static void cargaIngresos() {
        //INSTANCIO OPERACIONES DE INGRESO
        OperacionDeIngreso i1, i2, i3;
        Categoria donacion = RepositorioCriterios.getInstancia().buscar("TipoIngreso").buscar("donacion");
        i1 = new OperacionDeIngreso("Donacion 1", 3500d, LocalDate.parse("2018-02-27"));
        i1.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i1.agregarCategoria(donacion);
        i2 = new OperacionDeIngreso("Donacion 2", 4000d, LocalDate.parse("2018-02-22"));
        i2.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));
        i2.agregarCategoria(donacion);
        i3 = new OperacionDeIngreso("Operacion 3", 2500d, LocalDate.parse("2018-02-23"));
        i3.agregarCriterio(new CriterioAceptacion(TipoCriterio.SIN_RESTRICCION));

        RepositorioIngresos repoIngresos = RepositorioIngresos.getInstancia();

        repoIngresos.agregar(i1);
        repoIngresos.agregar(i2);
        repoIngresos.agregar(i3);
    }

    private static void cargaProveedores(){
        RepositorioProveedores repoProveedores = RepositorioProveedores.getInstancia();
        repoProveedores.agregar(new Proveedor("Coto", 1, "Calle Falsa 123"));
        repoProveedores.agregar(new Proveedor("Disco", 2, "Siempre Muerta 69"));
        repoProveedores.agregar(new Proveedor("My Crosoft", 3, "Wachin Ton 433"));
    }

    private static void cargaUsuarios() {
        RepositorioDeUsuarios repoUsuarios = RepositorioDeUsuarios.getInstancia();
        repoUsuarios.agregar(new Usuario("admin","admin123",RolUsuario.ADMIN));
        repoUsuarios.agregar(new Usuario("operador","operador456",RolUsuario.ESTANDAR));
        repoUsuarios.agregar(new Usuario("api_user", "api123456", RolUsuario.ESTANDAR));
    }

}
