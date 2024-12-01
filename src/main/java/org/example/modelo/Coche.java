package org.example.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Coche {
    @Id
    @GeneratedValue
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToOne(cascade = CascadeType.PERSIST)
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

    public Coche() {

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
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coche coche)) return false;
        return id == coche.id && Objects.equals(matricula, coche.matricula) && Objects.equals(marca, coche.marca) && Objects.equals(modelo, coche.modelo) && Objects.equals(fecha, coche.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, marca, modelo, fecha, propietario);
    }
}
