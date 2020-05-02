package grupo6.seguridad;

public class ValidacionLongitud implements Validacion {
    int longMin;

    public ValidacionLongitud(int longMin){
        this.longMin = longMin;
    }

    @Override
    public Boolean validar(String pass) {
        return pass.length() >= longMin;
    }
}
