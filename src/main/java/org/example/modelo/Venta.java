package org.example.modelo;

import java.util.Date;

public class Venta {
    private int id;
    private Coche cocheVendido;
    private Cliente clienteVendido;
    private Date fecha;
    private double precio;

    // Constructor vacío
    public Venta() {}

    // Constructor con parámetros
    public Venta(int id, Coche cocheVendido, Cliente clienteVendido, Date fecha, double precio) {
        this.id = id;
        this.cocheVendido = cocheVendido;
        this.clienteVendido = clienteVendido;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coche getCocheVendido() {
        return cocheVendido;
    }

    public void setCocheVendido(Coche cocheVendido) {
        this.cocheVendido = cocheVendido;
    }

    public Cliente getClienteVendido() {
        return clienteVendido;
    }

    public void setClienteVendido(Cliente clienteVendido) {
        this.clienteVendido = clienteVendido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
