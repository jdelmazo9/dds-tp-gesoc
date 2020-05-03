package grupo6.seguridad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ValidacionPeoresContraseñas implements Validacion {

    ArrayList<String> peoresContraseñas;

    public ValidacionPeoresContraseñas(){
        this.peoresContraseñas = new ArrayList<>();
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(ConstantesSeguridad.pathArchivoPeoresContraseñas);
            BufferedReader reader = new BufferedReader(fileReader);
            String s;
            while ( (s = reader.readLine()) != null ){
                peoresContraseñas.add(s);
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean validar(final String pass) {
        return (peoresContraseñas.stream().noneMatch(p -> p.equals(pass)));
    }

}
