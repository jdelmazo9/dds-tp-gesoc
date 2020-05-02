package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionRegExTest {

    Validacion regexVal;

    @BeforeEach
    void setUp() {
        regexVal = new ValidacionRegEx();
    }

    @Test
    void validacionRegExTest() {
        assertFalse(regexVal.validar("asdas1Adasd"), "La contraseña debe contener al menos un caracter especial");
        assertFalse(regexVal.validar("aA1$"), "La contraseña debe tener al menos 8 caracteres");
        assertFalse(regexVal.validar("asda s1Adasd"), "La contraseña no puede contener espacios");
        assertTrue(regexVal.validar("asdas1Adasd#"), "La contraseña es valida");
        assertTrue(regexVal.validar("12AA$12aa"), "La contraseña es valida");
    }

}
