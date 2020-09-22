package grupo6.dominio.repositorios.factories;

//import config.Config;
import grupo6.seguridad.Usuario;
import grupo6.dominio.repositorios.RepositorioDeUsuarios;
import grupo6.dominio.repositorios.daos.DAO;
//import grupo6.dominio.repositorios.daos.DAOHibernate;
import grupo6.dominio.repositorios.daos.DAOMemoria;
import grupo6.dominio.repositorios.testMemoData.Data;

public class FactoryRepositorioUsuarios {
    private static RepositorioDeUsuarios repo;

    static {
        repo = null;
    }

    public static RepositorioDeUsuarios get(){
        if(repo == null){
//            if(Config.useDataBase){
//                DAO<Usuario> dao = new DAOHibernate<>(Usuario.class);
//                repo = new RepositorioDeUsuarios(dao);
//            }
            if(true){
                repo = new RepositorioDeUsuarios(new DAOMemoria<>(Data.getData(Usuario.class)));
            }
        }
        return repo;
    }
}
