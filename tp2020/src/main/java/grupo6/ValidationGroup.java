package grupo6;

import java.util.List;

public class ValidationGroup implements Validation {

    private List<Validation> validators;

    @Override
    public Boolean validate(String pass) {
        return validators.stream()
        .map(val -> val.validate(pass))
        .allMatch(v -> v == true);
    }
    
    public void add_validator(Validation val){
        this.validators.add(val);
        return;
    }
}