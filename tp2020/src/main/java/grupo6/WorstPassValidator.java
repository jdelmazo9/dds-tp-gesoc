package grupo6;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import grupo6.Validation;

public class WorstPassValidator implements Validation{

    ArrayList<String> worst;

    public WorstPassValidator(){
        this.worst = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File("../media/worstPass.txt"));
            while (scanner.hasNextLine()){
                worst.add(scanner.next());
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        
    }

    @Override
    public Boolean validate(final String pass) {
        return (worst.stream().noneMatch(p -> p == pass));
    }
    
}