package org.example.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private List<Coche> coches;

    public Cliente(String dni, String nombre,String apellidos, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.coches = new ArrayList<>();
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void addCoche(Coche coche) {
        coches.add(coche);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", coches=" + coches +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return id == cliente.id && Objects.equals(dni, cliente.dni) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellidos, cliente.apellidos) && Objects.equals(telefono, cliente.telefono) && Objects.equals(coches, cliente.coches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, apellidos, telefono, coches);
    }
}
