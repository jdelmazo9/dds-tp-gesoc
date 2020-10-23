package grupo6.dominio.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ResultadoValidacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public Boolean validacionOK;
    public int idEgreso;
    String mensajeValidacion;
    LocalDateTime timestampValidacion;

    public ResultadoValidacion(){}

    public ResultadoValidacion(int idEgreso){
        this.idEgreso = idEgreso;
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
