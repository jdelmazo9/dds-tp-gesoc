package grupo6.seguridad;

public class ValidacionRegEx implements Validacion {

    /* regex se asegura que tenga un numero, una letra minuscula una mayuscula un caracter espacial y al menos 8 caracteres*/
    private final String regex;

    public ValidacionRegEx(String regex){
        this.regex = regex;
    }

    public ValidacionRegEx(){
        this.regex = SecurityConstants.defRegexValidation;
    }

    @Override
    public Boolean validar(final String pass){
        return pass.matches(regex);
    }

}
