package grupo6.vinculaciones;

import java.util.Comparator;

public interface ComparadorOperaciones extends Comparator<OperacionExt> {
    int compare(OperacionExt o1, OperacionExt o2);
}
