package grupo6.dominio.repositorios.daos;

import db.EntityManagerHelper;
import grupo6.dominio.entidades.MedioDePago;
import grupo6.dominio.repositorios.BusquedaCondicional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DAOHibernate<T> implements DAO<T> {
    private Class<T> type;

    public DAOHibernate(Class<T> type){
        this.type = type;
    }

    @Override
    public List<T> buscarTodos() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> critera = builder.createQuery(this.type);
        critera.from(type);
        List<T> entities = em.createQuery(critera).getResultList();
//        EntityManagerHelper.closeEntityManager();
        return entities;
    }

    @Override
    public T buscar(int id) {
        EntityManagerHelper.getEntityManager().clear();
//        EntityManagerHelper.beginTransaction();
        T result = EntityManagerHelper.getEntityManager().find(type, id);
//        EntityManagerHelper.commit();
//        EntityManagerHelper.closeEntityManager();
        return result;
    }

    @Override
    public T buscar(BusquedaCondicional condicional) {
        T result = (T) EntityManagerHelper.getEntityManager().createQuery(condicional.getCondicionCritero()).getSingleResult();
//        EntityManagerHelper.closeEntityManager();
        return result;
    }

    @Override
    public List<T> buscarTodos(BusquedaCondicional condicional) {
        List<T> result = (List<T>) EntityManagerHelper.getEntityManager().createQuery(condicional.getCondicionCritero()).getResultList();;
//        EntityManagerHelper.closeEntityManager();
        return result;
    }

    @Override
    public void agregar(Object unObjeto) {
        EntityManagerHelper.getEntityManager();
//        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(unObjeto);
//        EntityManagerHelper.commit();
//        EntityManagerHelper.closeEntityManager();
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        em.getTransaction().begin();
//        em.persist(unObjeto);
//        em.getTransaction().commit();
//        EntityManagerHelper.getEntityManager().getTransaction().begin();
//        EntityManagerHelper.getEntityManager().persist(unObjeto);
//        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void modificar(Object unObjeto) {
//        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.merge(unObjeto);
//        EntityManagerHelper.commit();
//        EntityManagerHelper.closeEntityManager();
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        em.getTransaction().begin();
//        em.merge(unObjeto);
//        em.getTransaction().commit();
//        EntityManagerHelper.getEntityManager().getTransaction().begin();
//        EntityManagerHelper.getEntityManager().merge(unObjeto);
//        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void eliminar(Object unObjeto) {
//        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.delete(unObjeto);
//        EntityManagerHelper.commit();
//        EntityManagerHelper.closeEntityManager();
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        em.getTransaction().begin();
//        em.remove(unObjeto);
//        em.getTransaction().commit();
//        EntityManagerHelper.getEntityManager().getTransaction().begin();
//        EntityManagerHelper.getEntityManager().remove(unObjeto);
//        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

//    public void eliminarTodos(){
//        EntityManagerHelper.
//    }
}
