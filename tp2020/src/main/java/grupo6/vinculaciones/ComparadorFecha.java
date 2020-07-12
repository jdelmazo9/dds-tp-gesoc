package grupo6.vinculaciones;

import java.util.Comparator;

public class ComparadorFecha implements ComparadorSimple{

    @Override
    public int compare(OperacionExt o1, OperacionExt o2) {
        return o1.fecha.compareTo(o2.fecha);
    }
}
