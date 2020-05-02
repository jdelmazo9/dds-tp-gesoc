package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionCompuestaTest {
    ValidacionCompuesta validacionCompuesta;

    @BeforeEach
    void setUp() {
        validacionCompuesta = new ValidacionCompuesta();
        validacionCompuesta.agregarValidacion(new ValidacionRegEx());
        validacionCompuesta.agregarValidacion(new ValidacionPeoresContraseñas());
        validacionCompuesta.agregarValidacion(new ValidacionLongitud(15));
    }

    @Test
    void validacionCompuestaTest(){
        assertFalse(validacionCompuesta.validar("12AA$12aa"), "La contraseña no cumple con la longitud requerida");
        assertFalse(validacionCompuesta.validar("123456789012345"), "La contraseña no cumple con la RegEx");
        assertFalse(validacionCompuesta.validar("magic"), "La contraseña no cumple con ningun criterio");
        assertTrue(validacionCompuesta.validar("$magic12MAGIC12$"), "La contraseña cumple con todos los criterios");
    }
}
