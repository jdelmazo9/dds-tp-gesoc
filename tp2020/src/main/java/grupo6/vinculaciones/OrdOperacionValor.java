package grupo6.vinculaciones;

import java.util.Comparator;

public class OrdOperacionValor implements Comparator<OperacionDTO>{

    @Override
    public int compare(OperacionDTO o1, OperacionDTO o2) {
        return o1.monto.compareTo(o2.monto);
    }
}
