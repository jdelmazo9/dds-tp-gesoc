package grupo6.dominio;

import java.util.ArrayList;

public class Empresa extends EntidadJuridica {
    private Integer cantidadPersonal;
    private String actividad;
    private Double promedioVentasAnual;
    private TipoEmpresa tipoEmpresa;

    public Empresa(String razonSocial, String cuit, String nombreFicticio, ArrayList<EntidadBase> entidadesBase, String direccionPostal, int codigoIGJ, Integer cantidadPersonal, String actividad, Double promedioVentasAnual) {
        super(razonSocial, cuit, nombreFicticio, entidadesBase, direccionPostal, codigoIGJ);
        this.cantidadPersonal = cantidadPersonal;
        this.actividad = actividad;
        this.promedioVentasAnual = promedioVentasAnual;
    }

    public int getCantidadPersonal() {
        return cantidadPersonal;
    }

    public void setCantidadPersonal(int cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public double getPromedioVentasAnual() {
        return promedioVentasAnual;
    }

    public void setPromedioVentasAnual(double promedioVentasAnual) {
        this.promedioVentasAnual = promedioVentasAnual;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }


    public TipoEmpresa determinarTipoEmpresa (){
        tipoEmpresa = CalculadoraTipoEmpresa.calcularTipoEmpresa(cantidadPersonal, actividad, promedioVentasAnual);
        return tipoEmpresa;
    }
}
