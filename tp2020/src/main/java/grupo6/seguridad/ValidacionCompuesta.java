package grupo6.seguridad;

import java.util.ArrayList;

public class ValidacionCompuesta implements Validacion {

    private ArrayList<Validacion> validaciones;

    public ValidacionCompuesta(){
        validaciones = new ArrayList<>();
    }

    @Override
    public Boolean validar(String pass) {
        return validaciones.stream()
        .allMatch(v -> v.validar(pass));
    }

    public void agregarValidacion(Validacion val){
        validaciones.add(val);
    }
}
