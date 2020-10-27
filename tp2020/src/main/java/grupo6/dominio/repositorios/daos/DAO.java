package grupo6.dominio.repositorios.daos;

import grupo6.dominio.repositorios.BusquedaCondicional;

import java.util.List;

public interface DAO<T> {
    List<T> buscarTodos();
    List<T> buscarTodos(BusquedaCondicional condicional);
    T buscar(int id);
    T buscar(BusquedaCondicional condicional);
    void agregar(Object unObjeto);
    void modificar(Object unObjeto);
    void eliminar(Object unObjeto);
}
