package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionLongitudTest {

    Validacion minLongVal;

    @BeforeEach
    void setUp() {
        minLongVal = new ValidacionLongitud(5);
    }

    @Test
    void validacionLongitudTest(){
        assertTrue(minLongVal.validar("asdas1A#dasd"), "La contraseña es valida");
        assertTrue(minLongVal.validar("asda%"), "La contraseña es valida");
        assertTrue(minLongVal.validar("as da"), "La contraseña es valida");
        assertFalse(minLongVal.validar("asad"), "La contraseña debe contener al menos 5 caracteres");
    }

}

