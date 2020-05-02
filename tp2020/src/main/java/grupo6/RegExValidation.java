package grupo6;

public class RegExValidation implements Validation {

    /* regex se asegura que tenga un numero, una letra minuscula una mayuscula un caracter espacial y al menos 8 caracteres*/ 
    private final String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

    @Override
    public Boolean validate(final String pass) {
        
        return pass.matches(regex);
    }

    
    
}