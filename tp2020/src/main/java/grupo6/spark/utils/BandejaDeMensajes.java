package grupo6.spark.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import grupo6.dominio.entidades.ResultadoValidacion;

public class BandejaDeMensajes {
    private ArrayList<ResultadoValidacion> resultados;

    public BandejaDeMensajes(){
        resultados = new ArrayList<ResultadoValidacion>();
    }

    public void agregarMensaje(ResultadoValidacion resultado){
        resultados.add(resultado);
    }

    public List<ResultadoValidacion> obtenerMensajes(){
        return resultados;
    }

    public ArrayList<ResultadoValidacion> obtenerMensajes(int idEgreso){
        return Lists.newArrayList(resultados.stream().filter(v -> v.idEgreso == idEgreso).collect(Collectors.toList()));
    }

    /*public List<Mensaje> obtenerPagina(int pagina, int mensajesPorPagina){
        return resultados.subList(pagina*mensajesPorPagina, (pagina+1)*mensajesPorPagina - 1);
    }
*/
    public int cantidadMensajes(){
        return resultados.size();
    }

    public String leerPrimerMensaje() {
        return resultados.get(0).get_mensaje();
    }
}
