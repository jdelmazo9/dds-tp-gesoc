package grupo6.dominio.entidades;

import java.util.ArrayList;

public abstract class EntidadJuridica {
    private String razonSocial;
    private String cuit;
    private String nombreFicticio;
    private ArrayList<EntidadBase> entidadesBase;
    private String direccionPostal;
    private int codigoIGJ;


    public EntidadJuridica (String razonSocial, String cuit, String nombreFicticio, ArrayList<EntidadBase> entidadesBase, String direccionPostal, int codigoIGJ) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.nombreFicticio = nombreFicticio;
        this.entidadesBase = entidadesBase;
        this.direccionPostal = direccionPostal;
        this.codigoIGJ = codigoIGJ;
    }

    public EntidadJuridica (String razonSocial, String cuit, String nombreFicticio, String direccionPostal, int codigoIGJ) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.nombreFicticio = nombreFicticio;
        this.direccionPostal = direccionPostal;
        this.codigoIGJ = codigoIGJ;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }

    public void setNombreFicticio(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }

    public ArrayList<EntidadBase> getEntidadesBase() {
        return entidadesBase;
    }

    public void setEntidadesBase(ArrayList<EntidadBase> entidadesBase) {
        this.entidadesBase = entidadesBase;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public int getCodigoIGJ() {
        return codigoIGJ;
    }

    public void setCodigoIGJ(int codigoIGJ) {
        this.codigoIGJ = codigoIGJ;
    }
}
