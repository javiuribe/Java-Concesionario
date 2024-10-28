package org.example.modelo;

import java.util.Date;

public class Coche {
    private int matricula;
    private String marca;
    private String modelo;
    private Date fecha;
    private Cliente propietario;

    public Coche(int matricula, String marca, String modelo, Date fecha, Cliente propietario) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fecha = fecha;
        this.propietario = propietario;
    }

    public int getMatricula() {
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

    public void setMatricula(int matricula) {
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
}
