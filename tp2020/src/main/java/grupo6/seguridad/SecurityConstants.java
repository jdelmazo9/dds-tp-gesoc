package grupo6.seguridad;

public final class SecurityConstants {
    //Filepaths
    public static final String worstPasswordsFilepath = "media/worstPass.txt";

    //Default values
    /*
        Default Regular Expression. The requirements are as follows:
            1) Password must contain at least 8 characters
            2) Password must contain at least 1 number
            3) Password must contain at least 1 upper case letter
            4) Password must contain at least 1 lower case letter
            5) Password must contain at least 1 special character
            6) Password must not contain any spaces
     */
    public static final String defRegexValidation = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

}
