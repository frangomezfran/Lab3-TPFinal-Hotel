package com.company;


import java.util.ArrayList;
import java.util.Hashtable;

public class Habitacion {

    private int cantCamas;
    private int piso;
    private char letra;
    Hashtable tipoHabitacion=new Hashtable();
    private double precio;
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private EstadoHabitacion estadoHabitacion;

    //--------------- Constructor ---------------
    public Habitacion(int cantCamas, int piso, char letra) {
        this.cantCamas = cantCamas;
        this.piso = piso;
        this.letra = letra;
    }

    //--------------- Cant Camas ---------------
    public int getCantCamas() {
        return cantCamas;
    }

    public void setCantCamas(int cantCamas) {
        this.cantCamas = cantCamas;
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

//--------------- Metodos ---------------

    //establecer precio segun el tipo de habitacion que sea


    public void establecerPrecio(Object TipoHabitacion_)
    {
        //voy a usar hashTable porque es mas optimo para este caso
        tipoHabitacion.put("individual",500);
        tipoHabitacion.put("doble",700);
        tipoHabitacion.put("triple",900);
        tipoHabitacion.put("quad",1050);
        tipoHabitacion.put("queen",2300);
        tipoHabitacion.put("king",3500);
        tipoHabitacion.put("twin",9000);
        tipoHabitacion.put("doblete",10500);
        tipoHabitacion.put("estudio",22500);


        if(tipoHabitacion.containsKey(TipoHabitacion_))//si la key esta en el hashTable
        {
            //establezco el precio segun que tipo de habitacion que sea
            this.precio=(double) tipoHabitacion.get(TipoHabitacion_);

        }
    }

}