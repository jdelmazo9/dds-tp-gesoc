package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationGroupTest {
    ValidationGroup validationGroup;

    @BeforeEach
    void setUp() {
        validationGroup = new ValidationGroup();
        validationGroup.addValidator(new ValidacionRegEx());
        validationGroup.addValidator(new WorstPasswordsValidation());
        validationGroup.addValidator(new LongValidation(15));
    }

    @Test
    void validationGroupTest(){
        assertFalse(validationGroup.validar("12AA$12aa"), "La contraseña no cumple con la longitud requerida");
        assertFalse(validationGroup.validar("123456789012345"), "La contraseña no cumple con la RegEx");
        assertFalse(validationGroup.validar("magic"), "La contraseña no cumple con ningun criterio");
        assertTrue(validationGroup.validar("$magic12MAGIC12$"), "La contraseña cumple con todos los criterios");
    }
}
