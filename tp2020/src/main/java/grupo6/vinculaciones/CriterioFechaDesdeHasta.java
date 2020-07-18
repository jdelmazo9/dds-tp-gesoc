package grupo6.vinculaciones;

import java.time.LocalDate;

public class CriterioFechaDesdeHasta implements CriterioAceptacionEgresos{

    private LocalDate fechaDesde, fechaHasta;

    public CriterioFechaDesdeHasta(LocalDate fechaDesde, LocalDate fechaHasta){
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public boolean acepta(EgresoExt egreso) {
        return !(egreso.fecha.isBefore(fechaDesde) || egreso.fecha.isAfter(fechaHasta));
    }
}
