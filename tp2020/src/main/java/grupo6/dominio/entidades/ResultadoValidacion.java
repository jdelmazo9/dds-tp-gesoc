package grupo6.dominio.entidades;

import java.time.LocalDateTime;

public class ResultadoValidacion {
    public Boolean validacionOK;
    String mensajeValidacion;
    LocalDateTime timestampValidacion;

    public ResultadoValidacion(){
        this.timestampValidacion = LocalDateTime.now();
    }

    public void set_resultado(Boolean res){
        validacionOK = res;
    }

    public void set_mensaje_resultado(String mensaje){
        mensajeValidacion = mensaje;
    }

    public Boolean get_resultado(){
        return validacionOK;
    }

    public String get_mensaje(){
        return mensajeValidacion;
    }

    public LocalDateTime get_timestamp(){
        return timestampValidacion;
    }
}
