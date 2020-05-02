package grupo6.seguridad;

public class ValidacionRegEx implements Validacion {

    private final String regex;

    public ValidacionRegEx(String regex){
        this.regex = regex;
    }

    public ValidacionRegEx(){
        this.regex = ConstantesSeguridad.defRegex;
    }

    @Override
    public Boolean validar(final String pass){
        return pass.matches(regex);
    }

}
