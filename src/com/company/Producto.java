package com.company;

public class Producto {
    private int stock;
    private String nombre;
    private double precio;
    private int cantidadAsolicitar;

    //--------------- Constructor ---------------
    public Producto(int stock, String nombre, double precio,int cantidadAsolicitar) {
        this.stock = stock;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadAsolicitar = cantidadAsolicitar;
    }

    //--------------- Stock ---------------
    public int getStock() {
        return stock;
    }
    public void setStock(int cantidadRetirada) {
        this.stock -=cantidadRetirada;
    }

    //--------------- Nombre ---------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //--------------- Precio ---------------
    //devuelvo el precio del producto por la cantidad de veces que se solicito
    public double getPrecio() {
        return precio*cantidadAsolicitar;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadAsolicitar() {
        return cantidadAsolicitar;
    }

    public void setCantidadAsolicitar(int cantidadAsolicitar) {
        this.cantidadAsolicitar = cantidadAsolicitar;
    }

    //--------------- Metodos ---------------

}
