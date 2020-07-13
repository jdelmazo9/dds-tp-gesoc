package grupo6.vinculaciones;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparadorMix implements ComparadorOperaciones{
    private ArrayList<ComparadorSimple> comparadores;

    public ComparadorMix(ArrayList<ComparadorSimple> comparadores){
        this.comparadores = comparadores;
    }

    public ComparadorMix(){
        this.comparadores = new ArrayList<>();
    }

    public int compare(OperacionExt o1, OperacionExt o2) {
        int r = 0;
        for (ComparadorSimple comparador: comparadores) {
            r = comparador.compare(o1, o2);
            if(r != 0)
                break;
        }
        return r;
    }

    public void agregarComparador(ComparadorSimple comparador){
        comparadores.add(comparador);
    }
}
