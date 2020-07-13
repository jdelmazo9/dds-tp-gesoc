package grupo6.vinculaciones;

import java.util.Comparator;

public class ComparadorValor implements ComparadorSimple{
    public int compare(OperacionExt o1, OperacionExt o2) {
        return o1.monto.compareTo(o2.monto);
    }
}
