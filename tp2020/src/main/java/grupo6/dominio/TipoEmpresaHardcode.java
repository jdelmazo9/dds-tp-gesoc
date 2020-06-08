package grupo6.dominio;

import java.util.ArrayList;
import java.util.Arrays;

public class TipoEmpresaHardcode implements AdaptadorClasificacionEmpresa {

    ArrayList<Actividad> miLista = new ArrayList<Actividad>() {
        {
            add(new Actividad("Construcción",
                new ArrayList<Integer>(Arrays.asList(12, 45, 200, 590)),
                new ArrayList<Double>(Arrays.asList(15230000d, 90310000d, 503880000d, 755740000d))))
            ;
            add(new Actividad("Servicios",
                new ArrayList<Integer>(Arrays.asList(7, 30, 165, 535)),
                new ArrayList<Double>(Arrays.asList(8500000d, 50950000d, 425170000d, 607210000d))))
            ;
            add(new Actividad("Comercio",
                new ArrayList<Integer>(Arrays.asList(7, 35, 125, 345)),
                new ArrayList<Double>(Arrays.asList(29740000d, 178860000d, 1502750000d, 2146810000d))))
            ;
            add(new Actividad("Industria y Minería",
                new ArrayList<Integer>(Arrays.asList(15, 60, 235, 655)),
                new ArrayList<Double>(Arrays.asList(26540000d, 190410000d, 1190330000d, 1739590000d))))
            ;
            add(new Actividad("Agropecuario",
                new ArrayList<Integer>(Arrays.asList(5, 10, 50, 215)),
                new ArrayList<Double>(Arrays.asList(12890000d, 48480000d, 345430000d, 547890000d))))
            ;
        }

    };
    public Actividad obtenerActividad(String actividad) {
        Actividad actARetornar;
        for (Actividad act : miLista) {
            if(actividad.equals(act.actividad)) {
                return act;
            }
        }
        //MANEJAR EXCEPCION
        return null;
    }
}
