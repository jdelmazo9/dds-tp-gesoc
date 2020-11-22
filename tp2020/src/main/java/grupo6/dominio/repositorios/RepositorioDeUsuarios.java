package grupo6.dominio.repositorios;

import grupo6.seguridad.Usuario;
import grupo6.seguridad.Excepciones.UsuarioInexistenteException;
import grupo6.dominio.repositorios.daos.DAO;
import grupo6.dominio.repositorios.daos.DAOHibernate;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mindrot.jbcrypt.BCrypt;

public class RepositorioDeUsuarios extends Repositorio<Usuario> {

    private static RepositorioDeUsuarios yoMismo = null;

    public RepositorioDeUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public static RepositorioDeUsuarios getInstancia() {
        if (yoMismo == null) {
            yoMismo = new RepositorioDeUsuarios(new DAOHibernate<>(Usuario.class));
        }
        return yoMismo;
    }

    public void agregar(Usuario u) {
        this.dao.agregar(u);
    }

    public Boolean existe(String nombreDeUsuario, String contrasenia) {
        return buscarUsuario(nombreDeUsuario, contrasenia) != null;
    }

    public Usuario buscarUsuario(String nombreDeUsuario, String contrasenia) {
        Usuario user;
        try {
            user = this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario, contrasenia));
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombreDeUsuario);
        
        usuarioQuery.where(condicionNombreDeUsuario);
        //Predicate condicionContrasenia = criteriaBuilder.isTrue(criteriaBuilder.literal(BCrypt.checkpw(contrasenia, condicionRaiz.get("contraseniaHash").toString())));

        //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        //usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }
    
}
