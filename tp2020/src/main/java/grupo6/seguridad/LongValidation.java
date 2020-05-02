package grupo6.seguridad;

public class LongValidation implements Validacion {
    int minLong;

    public LongValidation(int minLong){
        this.minLong = minLong;
    }

    @Override
    public Boolean validar(String pass) {
        return pass.length() >= minLong;
    }
}
