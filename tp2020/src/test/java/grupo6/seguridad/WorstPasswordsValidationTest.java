package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorstPasswordsValidationTest {

    Validacion worstPasswordsVal;

    @BeforeEach
    void setUp() {
        worstPasswordsVal = new WorstPasswordsValidation();
    }

    @Test
    void worstPasswordsTest(){
        assertFalse(worstPasswordsVal.validar("midnight"), "La contraseña se encuentra en el top 10k de peores contraseñas");
        assertFalse(worstPasswordsVal.validar("987654"), "La contraseña se encuentra en el top 10k de peores contraseñas");
        assertTrue(worstPasswordsVal.validar("midn$ght"), "La contraseña es valida");
    }
}
