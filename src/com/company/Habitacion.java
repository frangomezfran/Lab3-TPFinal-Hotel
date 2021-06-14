package com.company;

import java.util.ArrayList;
import java.util.Hashtable;

public class Habitacion {

    private TipoHabitacion tipoHabitacion;
    private int piso;
    private char letra;
    private double precio;
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private EstadoHabitacion estadoHabitacion;


    //--------------- Constructor ---------------
    public Habitacion(TipoHabitacion tipoHabitacion, int piso, char letra) {
        this.tipoHabitacion=tipoHabitacion;
        this.piso = piso;
        this.letra = letra;
        this.estadoHabitacion=EstadoHabitacion.DISPONIBLE;
    }

    //--------------- Piso ---------------
    public int getPiso() {
        return piso;
    }
    public void setPiso(int piso) {
        this.piso = piso;
    }

    //--------------- Letra ---------------
    public char getLetra() {
        return letra;
    }
    public void setLetra(char letra) {
        this.letra = letra;
    }

    //--------------- Precio ---------------
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //--------------- Lista de productos ---------------
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    //--------------- Estado de la Habitacion ---------------
    public EstadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }
    public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    //--------------- Tipo de Habitacion ---------------
    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }
    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }


    //--------------- Metodos ---------------

    //establecer precio segun el tipo de habitacion que sea

    public void establecerPrecio(TipoHabitacion tipoHabitacion,double precio)
    {

    }

    public String muestraHabitacion(){

        return "[ Tipo: "+tipoHabitacion.getTipo()+" | CantMax de Personas: "+tipoHabitacion.getCantPersonas()+" | Piso: "+getPiso()+" | Letra: "+getLetra()+" | " +
                "Precio: "+getPrecio()+" ]";

    }

    @Override
    public String toString() {
        return "Habitacion{" +
                " Tipo=" + tipoHabitacion.getTipo() +
                ", piso=" + piso +
                ", letra=" + letra +
                ", precio=" + precio +
                ", listaProductos=" + listaProductos +
                ", estadoHabitacion=" + estadoHabitacion +
                '}';
    }
}