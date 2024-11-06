package org.example.modelo;

import java.util.Date;

public class Coche {
    private String matricula;
    private String marca;
    private String modelo;
    private Date fecha;
    private Cliente propietario;

    public Coche(String matricula, String marca, String modelo, Date fecha) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fecha = fecha;
    }
    public Coche(String matricula, String marca, String modelo, Date fecha, Cliente propietario) {
        this( matricula, marca, modelo, fecha);
        this.propietario = propietario;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
