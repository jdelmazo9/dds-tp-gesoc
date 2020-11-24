package grupo6.seguridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionPeoresContraseñasTest {

    Validacion validacionPeoresContraseñas;

    @BeforeEach
    void setUp() {
        validacionPeoresContraseñas = new ValidacionPeoresContraseñas();
    }

    @Test
    void peoresContraseñasTest(){
        assertFalse(validacionPeoresContraseñas.validar("midnight"), "La contraseña se encuentra en el top 10k de peores contraseñas");
        assertFalse(validacionPeoresContraseñas.validar("987654"), "La contraseña se encuentra en el top 10k de peores contraseñas");
        assertTrue(validacionPeoresContraseñas.validar("midn$ght"), "La contraseña es valida");
    }


}

