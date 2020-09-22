package grupo6.dominio.repositorios.testMemoData;

import grupo6.dominio.entidades.EntidadPersistente;
//import domain.entities.Rol;
import grupo6.seguridad.*;
import grupo6.dominio.repositorios.Repositorio;
import grupo6.dominio.repositorios.factories.FactoryRepositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataUsuario {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(usuarios.size() == 0) {
//            Repositorio<Rol> repoRol = FactoryRepositorio.get(Rol.class);

            Usuario jero = new Usuario("Jero","1234",RolUsuario.ADMIN);
            Usuario manu = new Usuario("Manu","1234",RolUsuario.ESTANDAR);
            Usuario zucco = new Usuario("Zucco","1234",RolUsuario.ADMIN);
            Usuario monluki = new Usuario("Lukimon","1234",RolUsuario.ADMIN);
            Usuario luquitas = new Usuario("Lucasnb","1234",RolUsuario.ADMIN);

//            lucas.setNombre("Lucas");
//            lucas.setApellido("Saclier");
//            lucas.setId(1);
//            lucas.setTelefono(1156585936);
//            lucas.setLegajo(112233);
//            lucas.setRol(repoRol.buscar(1));
//
//            Usuario eze = new Usuario();
//            eze.setNombre("Eze");
//            eze.setApellido("Escobar");
//            eze.setId(2);
//            eze.setTelefono(1156339837);
//            eze.setLegajo(112244);
//            eze.setRol(repoRol.buscar(2));
//
//            Usuario gaston = new Usuario();
//            gaston.setNombre("Gaston");
//            gaston.setApellido("Prieto");
//            gaston.setId(3);
//            gaston.setTelefono(1156449831);
//            gaston.setLegajo(112255);
//            gaston.setRol(repoRol.buscar(2));
//
//            Usuario ezeS = new Usuario();
//            ezeS.setNombre("Eze");
//            ezeS.setApellido("Sosa");
//            ezeS.setId(4);
//            ezeS.setTelefono(1156559832);
//            ezeS.setLegajo(112266);
//            ezeS.setRol(repoRol.buscar(2));

            addAll(jero, manu, zucco, monluki, luquitas);
        }
        return (List<EntidadPersistente>)(List<?>) usuarios;
    }

    private static void addAll(Usuario ... usuarios){
        Collections.addAll(DataUsuario.usuarios, usuarios);
    }
}
