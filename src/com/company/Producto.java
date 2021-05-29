package com.company;

public class Producto {
    private int stock;
    private String nombre;
    private double precio;

    //--------------- Constructor ---------------
    public Producto(int stock, String nombre, double precio) {
        this.stock = stock;
        this.nombre = nombre;
        this.precio = precio;
    }

    //--------------- Stock ---------------
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    //--------------- Nombre ---------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //--------------- Precio ---------------
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //--------------- Metodos ---------------

}
