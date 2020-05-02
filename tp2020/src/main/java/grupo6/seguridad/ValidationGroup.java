package grupo6.seguridad;

import java.util.ArrayList;

public class ValidationGroup implements Validacion {

    private ArrayList<Validacion> validators;

    public ValidationGroup(){
        validators = new ArrayList<Validacion>();
    }

    @Override
    public Boolean validar(String pass) {
        return validators.stream()
        .allMatch(v -> v.validar(pass));
    }

    public void addValidator(Validacion val){
        validators.add(val);
    }
}
