package grupo6.seguridad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ValidacionPeoresContraseñas implements Validacion {

    ArrayList<String> peoresContraseñas;

    public ValidacionPeoresContraseñas(){
        this.peoresContraseñas = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ConstantesSeguridad.pathArchivoPeoresContraseñas));
            String s;
            while ( (s = reader.readLine()) != null ){
                peoresContraseñas.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean validar(final String pass) {
        return (peoresContraseñas.stream().noneMatch(p -> p.equals(pass)));
    }

}
