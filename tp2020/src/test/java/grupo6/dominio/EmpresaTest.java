package grupo6.dominio;

import grupo6.dominio.entidades.Empresa;
import grupo6.dominio.entidades.TipoEmpresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmpresaTest {

    Empresa empresa;
    @BeforeEach
    void setUp() {
        empresa = new Empresa("Taligent SRL", "301598763783", "Tali", null,
            "1414", 12, 201, "Construcción", 503880001d);
    }

    @Test
    void calcularTipoEmpresaConstruccionTest() {
        assertEquals( TipoEmpresa.MedianaTramo2  , empresa.determinarTipoEmpresa());
    }
}
