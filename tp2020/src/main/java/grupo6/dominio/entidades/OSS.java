package grupo6.dominio.entidades;

import java.util.ArrayList;

public class OSS extends EntidadJuridica {
    public OSS(String razonSocial, String cuit, String nombreFicticio, ArrayList<EntidadBase> entidadesBase, String direccionPostal, int codigoIGJ) {
        super(razonSocial, cuit, nombreFicticio, entidadesBase, direccionPostal, codigoIGJ);
    }

    public OSS(String razonSocial, String cuit, String nombreFicticio, String direccionPostal, int codigoIGJ) {
        super(razonSocial, cuit, nombreFicticio, direccionPostal, codigoIGJ);
    }
}
