package grupo6.seguridad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WorstPasswordsValidation implements Validacion {

    ArrayList<String> worst;

    public WorstPasswordsValidation(){
        this.worst = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(SecurityConstants.worstPasswordsFilepath));
            String line;
            while ( (line = reader.readLine()) != null ){
                worst.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean validar(final String pass) {
        return (worst.stream().noneMatch(p -> p.equals(pass)));
    }

}
