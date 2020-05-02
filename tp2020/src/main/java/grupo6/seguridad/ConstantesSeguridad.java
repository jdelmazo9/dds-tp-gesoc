package grupo6.seguridad;

public final class ConstantesSeguridad {
    //Paths
    public static final String pathArchivoPeoresContraseñas = "media/worstPass.txt";

    //Valores por defecto
    /*
        Expresion regular por defecto. Los requerimientos son:
            1) Debe contener al menos 8 caracteres
            2) Debe contenere al menos un numero
            3) Debe contener al menos una letra mayuscula y una minuscula
            4) Debe contener al menos un caracter especial
            5) No debe contener ningun espacio en blanco
     */
    public static final String defRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

}
